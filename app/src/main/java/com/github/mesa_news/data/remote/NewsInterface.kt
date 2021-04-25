package com.github.mesa_news.data.remote

import com.github.mesa_news.data.models.PagedNews
import retrofit2.http.GET

interface NewsInterface {
    @GET("/client/news")
    suspend fun news(): PagedNews
}