package com.github.mesa_news.data.remote

import okhttp3.OkHttpClient


object HttpClient {
    val instance: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor)
                .build()
        }
}
