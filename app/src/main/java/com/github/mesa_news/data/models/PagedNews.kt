package com.github.mesa_news.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PagedNews(
    val pagination: Page,
    val data: List<New>
)