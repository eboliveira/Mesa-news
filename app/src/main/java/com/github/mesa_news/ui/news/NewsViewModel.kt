package com.github.mesa_news.ui.news

import androidx.lifecycle.ViewModel
import com.github.mesa_news.data.repositories.NewsRepository

class NewsViewModel: ViewModel() {
    val highlightedNews = NewsRepository.highlightedNews
    val news = NewsRepository.news
}
