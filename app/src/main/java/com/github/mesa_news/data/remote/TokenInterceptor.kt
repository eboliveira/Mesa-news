package com.github.mesa_news.data.remote

import com.github.mesa_news.data.local.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        val token = SharedPreferences.token
        if (token != null)
            builder.addHeader("Authorization", "Bearer ${SharedPreferences.token}")
        request = builder.build()
        return chain.proceed(request)
    }
}