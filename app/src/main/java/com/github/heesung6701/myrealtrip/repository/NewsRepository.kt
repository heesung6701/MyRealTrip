package com.github.heesung6701.myrealtrip.repository

import android.util.Log
import com.github.heesung6701.myrealtrip.model.News
import com.github.heesung6701.myrealtrip.network.NetworkHelper
import com.github.heesung6701.myrealtrip.network.dto.Rss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsRepository {
    fun getList(onUpdate: () -> Unit, onFinish: (List<News>?) -> Unit) {
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
                val col = news.map {  News(link = it.link.toString(),title = it.title.toString()) }
                col.forEach {
                    val link = it.link ?: return
                    GlobalScope.launch(Dispatchers.Main) {
                        val list = getOGTag(link)
                        if(list?.size != 2) return@launch
                        with(it) {
                            content = list[0]
                            thumbnail = list[1]
                        }
                        onUpdate()
                    }
                }
                onFinish(col)
            }

            override fun onFailure(call: Call<Rss>, t: Throwable) {
                t.printStackTrace()
                onFinish(null)
            }
        }
        call.enqueue(callback)
    }
    private suspend fun getOGTag(url: String): List<String>?{
        return withContext(Dispatchers.IO) {
            var imageUrl = ""
            var content = ""

            val con: Connection = Jsoup.connect(url)
            try{
                val doc = con.get()
                val ogTags = doc.select("meta[property^=og:]")
                if (ogTags.size <= 0) {
                    null
                } else {
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

                    listOf(content, imageUrl)
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}