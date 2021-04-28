package com.github.mesa_news.data.repositories

import com.github.mesa_news.data.local.DatabaseInterface
import com.github.mesa_news.data.remote.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import org.joda.time.Seconds

object NewsRepository {
    val highlightedNews = DatabaseInterface.getDatabase()?.newDao()?.getHighlighted()
    lateinit var lastRefreshAt: DateTime

    suspend fun refreshHighlighted() = withContext(Dispatchers.IO) {
        if (canRefresh()) {
            try {
                val newsDao = DatabaseInterface.getDatabase()?.newDao()
                newsDao ?: throw Exception("No dao found trying to refresh highlighted news")
                val news = Api.newsServices.highlights()
                newsDao.clean()
                newsDao.insertAll(news.data)
            } catch (e: Exception) {
                // TODO Implement error handling
                println(e)
            }
        }
    }

    private fun canRefresh(): Boolean {
        if (!NewsRepository::lastRefreshAt.isInitialized) {
            lastRefreshAt = DateTime.now()
            return true
        }
        val now = DateTime.now()
        return Seconds.secondsBetween(now, lastRefreshAt).seconds > 60.0
    }
}
