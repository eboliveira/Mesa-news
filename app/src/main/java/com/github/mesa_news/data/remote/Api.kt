package com.github.mesa_news.data.remote

import com.github.mesa_news.Environment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private val client = Retrofit.Builder()
        .baseUrl(Environment.apiUrl)
        .client(HttpClient.instance)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsServices: NewsInterface = client.create(NewsInterface::class.java)
}
