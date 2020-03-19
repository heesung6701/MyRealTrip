package com.github.heesung6701.myrealtrip.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.heesung6701.myrealtrip.R
import com.github.heesung6701.myrealtrip.main.adapter.NewsListAdapter
import com.github.heesung6701.myrealtrip.model.News
<<<<<<< HEAD
=======
import com.github.heesung6701.myrealtrip.network.NetworkHelper
import com.github.heesung6701.myrealtrip.repository.NewsRepository
>>>>>>> 2bef209... temp
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<News>(
        News(thumbnail = "https://pbs.twimg.com/profile_images/870563578704613376/8YPGkZbY.jpg",title = "title1",content = "content1"),
        News(thumbnail = "https://s.pstatic.net/static/www/mobile/edit/2020/0316/cropImg_166x108_26294546710170353.jpeg",title = "title2",content = "content2"),
        News(thumbnail = "https://s.pstatic.net/static/www/mobile/edit/2020/0316/cropImg_166x108_26265426473432757.jpeg",title = "title3",content = "content3"),
        News(thumbnail = "https://pbs.twimg.com/profile_images/870563578704613376/8YPGkZbY.jpg",title = "title4",content = "content4"),
        News(thumbnail = "https://pbs.twimg.com/profile_images/870563578704613376/8YPGkZbY.jpg",title = "title5",content = "content5"),
        News(thumbnail = "https://pbs.twimg.com/profile_images/870563578704613376/8YPGkZbY.jpg",title = "title6",content = "content6"))

    private var adapter : NewsListAdapter by Delegates.notNull()
<<<<<<< HEAD
=======
    private val newsRepository = NewsRepository()
>>>>>>> 2bef209... temp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_news.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(
            applicationContext,
            list
        )
        recyclerview_news.adapter = adapter

        layout_swipe_refresh.setOnRefreshListener {
<<<<<<< HEAD
            list.clear()
=======
            updateList()
        }
        updateList()
    }
    private fun updateList() {
        layout_swipe_refresh.isRefreshing = true
        newsRepository.getList {
            if(it == null) return@getList
            list.clear()
            list.addAll(it)
>>>>>>> 2bef209... temp
            adapter.notifyDataSetChanged()
            layout_swipe_refresh.isRefreshing = false
        }
    }
}
