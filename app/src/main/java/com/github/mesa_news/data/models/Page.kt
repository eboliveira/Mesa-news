package com.github.mesa_news.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val currentPage: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int
)