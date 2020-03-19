package com.github.heesung6701.myrealtrip.repository

import android.os.AsyncTask
import android.util.Log
import com.github.heesung6701.myrealtrip.model.News
import com.github.heesung6701.myrealtrip.network.NetworkHelper
import com.github.heesung6701.myrealtrip.network.dto.Rss
import org.jsoup.Connection
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class NewsRepository {
    fun getList(onFinish: (List<News>?) -> Unit) {
        val call: Call<Rss> = NetworkHelper.apiService.getNewsList()

        val callback: Callback<Rss> = object : Callback<Rss> {
            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                if (!response.isSuccessful) {
                    onFinish(null)
                    return
                }

                val result = response.body()
                if(result == null) {
                    onFailure(call, Exception("XML PARSE ERROR : No Rss Error"))
                    return
                }
                val channel = result.channel
                if(channel == null) {
                    onFailure(call, Exception("XML PARSE ERROR : No Channel Error"))
                    return
                }
                val news = channel.news
                if(news == null) {
                    onFailure(call, Exception("XML PARSE ERROR : No News Error"))
                    return
                }
                val col = ArrayList<News>()
                var cnt = news.size
                news.forEach {
                    val link = it.link ?: return
                    MyTask { list ->
                        cnt --
                        if(list?.get(0) == null){
                            Log.e("~~", list.toString())
                        }
                        col.add(
                            News(
                                title = it.title.toString(),
                                content = list?.get(0)?:"error",
                                thumbnail = list?.get(1)?:""
                            )
                        )
                        if(cnt == 0)
                            onFinish(col)
                    }.execute(link);
                }
            }

            override fun onFailure(call: Call<Rss>, t: Throwable) {
                t.printStackTrace()
                onFinish(null)
            }
        }
        call.enqueue(callback)
    }

}

// TODO NOT USER ASYNC TASK
private class MyTask(val callback: (List<String>?) -> Unit) : AsyncTask<String?, Void?, List<String>?>() {

    override fun onPostExecute(result: List<String>?) { //if you had a ui element, you could display the title
        callback(result)
    }

    override fun doInBackground(vararg params: String?): List<String>? {
        return getOGTag(params[0].toString())
    }
    private fun getOGTag(url: String): List<String>?{

        var imageUrl = ""
        var content = ""

        try {
            val con: Connection = Jsoup.connect(url)
            val doc = con.get()
            val ogTags = doc.select("meta[property^=og:]")
            if (ogTags.size <= 0) {
                return null
            }

            ogTags.forEach { tag ->
                when (tag.attr("property")) {
                    "og:image" -> {
                        imageUrl = tag.attr("content")
                    }
                    "og:description" -> {
                        content = tag.attr("content")
                    }
                }
            }
            return listOf(content, imageUrl)
        } catch (e: IOException) {
            return null
        }
    }
}