package com.github.heesung6701.myrealtrip.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.heesung6701.myrealtrip.R
import com.github.heesung6701.myrealtrip.model.News
import kotlinx.android.synthetic.main.li_news.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

<<<<<<< HEAD
        val news = intent.getParcelableExtra<News>(News.INTENT_NAME)
=======
        val news = intent.getParcelableExtra<News?>(News.INTENT_NAME)
        if(news == null){
            finish()
            return
        }
>>>>>>> 2bef209... temp
        with(news) {
            tv_title.text = title
            tv_content.text = content
        }
        Glide.with(this).load(news.thumbnail).into(iv_thumbnail)
        val ivList = listOf<TextView>(tv_keyword_1, tv_keyword_2, tv_keyword_3)
<<<<<<< HEAD
        news.keywords.forEachIndexed { index, keyword ->
=======
        news.keywords?.forEachIndexed { index, keyword ->
>>>>>>> 2bef209... temp
            ivList[index].text = keyword
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
