package com.github.mesa_news.data.remote

import okhttp3.OkHttpClient

class HttpClient : OkHttpClient() {
    init {
        Builder()
            .addInterceptor(TokenInterceptor())
            .build()
    }
}