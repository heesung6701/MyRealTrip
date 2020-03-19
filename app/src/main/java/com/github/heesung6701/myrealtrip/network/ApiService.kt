package com.github.heesung6701.myrealtrip.network

import com.github.heesung6701.myrealtrip.network.dto.Rss
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("/rss?hl=ko&gl=KR&ceid=KR:ko")
    fun getNewsList(): Call<Rss>
}