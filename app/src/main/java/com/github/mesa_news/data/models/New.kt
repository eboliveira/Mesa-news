package com.github.mesa_news.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class New(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String = "",

    val description: String = "",

    val content: String = "",

    val author: String = "",

    @ColumnInfo(name = "published_at")
    val publishedAt: String = "",

    val highlight: Boolean = false,

    val url: String = "",

    @ColumnInfo(name = "image_url")
    val imageUrl: String = ""
)