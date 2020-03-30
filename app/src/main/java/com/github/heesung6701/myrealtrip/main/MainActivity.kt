package com.github.heesung6701.myrealtrip.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.heesung6701.myrealtrip.R
import com.github.heesung6701.myrealtrip.main.adapter.NewsListAdapter
import com.github.heesung6701.myrealtrip.model.News
import com.github.heesung6701.myrealtrip.repository.NewsRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<News>()

    private val newsRepository = NewsRepository()
    private val compositeDisposable = CompositeDisposable()

    private var adapter: NewsListAdapter by Delegates.notNull()

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
            updateList()
        }
        updateList()
    }

    private fun updateList() {
        layout_swipe_refresh.isRefreshing = true
        val disposable = newsRepository.getList(onUpdate = { adapter.notifyDataSetChanged() },
            onFinish = {
                if (it == null) return@getList
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
                layout_swipe_refresh.isRefreshing = false
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
