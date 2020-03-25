package com.github.heesung6701.myrealtrip.main.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.heesung6701.myrealtrip.R
import com.github.heesung6701.myrealtrip.detail.DetailActivity
import com.github.heesung6701.myrealtrip.model.News
import com.github.heesung6701.myrealtrip.viewholder.NewsViewHolder

class NewsListAdapter(private val context: Context, private val dataList: List<News>) :
    RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.li_news, parent, false)

        return NewsViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = dataList[position]
        holder.setTitle(item.title)
        holder.setContent(item.content)
        holder.setKeywords(item.keywords ?: listOf())
        holder.setThumbnail(
            Glide.with(context)
                .load(item.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.img_news)
        )
        holder.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(News.INTENT_NAME, dataList[position])
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}