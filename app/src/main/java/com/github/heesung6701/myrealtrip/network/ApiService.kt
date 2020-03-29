package com.github.heesung6701.myrealtrip.network

import com.github.heesung6701.myrealtrip.network.dto.Rss
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/rss?hl=ko&gl=KR&ceid=KR:ko")
    fun getNewsList(): Observable<Rss>
}