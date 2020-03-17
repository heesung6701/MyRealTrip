package com.github.heesung6701.myrealtrip.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestBuilder
import kotlinx.android.synthetic.main.li_news.view.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivThumbnail: ImageView
    private val tvTitle: TextView
    private val tvContent: TextView
    private val keywords: List<TextView>

    init {
        with(itemView) {
            ivThumbnail = iv_thumbnail
            tvTitle = tv_title
            tvContent = tv_content
            keywords = listOf(tv_keyword_1, tv_keyword_2, tv_keyword_3)
        }
    }

    fun setThumbnail(requestBuilder: RequestBuilder<Drawable>) {
        requestBuilder.into(ivThumbnail)
    }
    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun setContent(content: String) {
        tvContent.text = content
    }

    fun setKeywords(list: List<String>) {
        list.forEachIndexed { index, str ->
            keywords[index].text = str
        }
    }
    fun setOnClickListener(method: () -> Unit) {
        itemView.setOnClickListener {
            method()
        }
    }
}