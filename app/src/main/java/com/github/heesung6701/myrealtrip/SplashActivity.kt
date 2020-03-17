package com.github.heesung6701.myrealtrip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timer().schedule(object: TimerTask() {
            override fun run() {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }, 1300L)
    }
}
