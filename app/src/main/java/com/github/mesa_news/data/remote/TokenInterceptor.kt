package com.github.mesa_news.data.remote

import com.github.mesa_news.data.local.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

object TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val token = SharedPreferences.token
        if (token != null)
            builder.addHeader("Authorization", "Bearer $token")
        return chain.proceed(builder.build())
    }
}
