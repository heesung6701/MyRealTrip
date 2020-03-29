package com.github.heesung6701.myrealtrip.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object NetworkHelper {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://news.google.com/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    val apiService: ApiService

    init {
        apiService = retrofit.create(ApiService::class.java)
    }
}