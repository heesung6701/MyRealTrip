package com.github.heesung6701.myrealtrip.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.github.heesung6701.myrealtrip.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val url: String? = intent.getStringExtra("url")
        if(url == null) {
            finish()
            return
        }
        webview.webViewClient = WebViewClient()
        webview.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
