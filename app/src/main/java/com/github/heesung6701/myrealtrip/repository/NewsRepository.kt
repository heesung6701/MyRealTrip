package com.github.heesung6701.myrealtrip.repository

import com.github.heesung6701.myrealtrip.model.News
import com.github.heesung6701.myrealtrip.network.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection
import org.jsoup.Jsoup

class NewsRepository {
    fun getList(onUpdate: () -> Unit, onFinish: (List<News>?) -> Unit) : Disposable {
        return NetworkHelper.apiService.getNewsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { rss ->
                val channel = rss.channel ?: return@subscribe
                val news = channel.news ?: return@subscribe
                val col = news.map {  News(link = it.link.toString(),title = it.title.toString()) }
                col.forEach {
                    val link = it.link
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
