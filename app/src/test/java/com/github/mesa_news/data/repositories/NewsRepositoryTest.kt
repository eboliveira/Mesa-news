package com.github.mesa_news.data.repositories

import androidx.lifecycle.LiveData
import com.github.mesa_news.data.local.DatabaseInterface
import com.github.mesa_news.data.local.daos.NewDao
import com.github.mesa_news.data.models.New
import com.github.mesa_news.data.models.PagedNews
import com.github.mesa_news.data.remote.Api
import com.github.mesa_news.data.remote.NewsInterface
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime
import org.junit.After
import org.junit.Before
import org.junit.Test

class NewsRepositoryTest {
    private val databaseMock = mockk<DatabaseInterface>()
    private val daoMock = mockk<NewDao>(relaxed = true)
    private val highlightedNewsMock = mockk<LiveData<List<New>>>()
    private val newsServicesMock = mockk<NewsInterface>()
    private val pagedNews = mockk<PagedNews>()
    private val news = listOf(mockk<New>(), mockk())

    @Before
    fun setupMocks() {
        mockkObject(DatabaseInterface)
        every { DatabaseInterface.getDatabase() } returns databaseMock
        every { databaseMock.newDao() } returns daoMock
        every { daoMock.getHighlighted() } returns highlightedNewsMock
        mockkObject(Api)
        every { Api.newsServices } returns newsServicesMock
        coEvery { newsServicesMock.news() } returns pagedNews
        every { pagedNews.data } returns news
    }

    @After
    fun  teardown() {
        clearAllMocks()

    }

    @Test
    fun `refresh do not call API when there is no database`() {
        every { DatabaseInterface.getDatabase() } returns null

        runBlocking {
            NewsRepository.refresh()
        }

        coVerify(exactly = 0) { newsServicesMock.news() }
    }

    @Test
    fun `refresh do not call API when refreshed too soon`() {
        NewsRepository.lastRefreshAt = DateTime.now().plusSeconds(58)

        runBlocking {
            NewsRepository.refresh()
        }

        coVerify(exactly = 0) { newsServicesMock.news() }
    }

    @Test
    fun `refresh call API when not refreshed too soon and there is DAO `() {
        NewsRepository.lastRefreshAt = DateTime.now().plusSeconds(62)

        runBlocking {
            NewsRepository.refresh()
        }

        coVerify(exactly = 1) { newsServicesMock.news() }
    }

    @Test
    fun `refresh insert on database when not refreshed too soon and there is DAO `() {
        NewsRepository.lastRefreshAt = DateTime.now().plusSeconds(62)

        runBlocking {
            NewsRepository.refresh()
        }

        coVerify(exactly = 1) { daoMock.insertAll(news) }
    }

    @Test
    fun `refresh clean database when not refreshed too soon and there is DAO `() {
        NewsRepository.lastRefreshAt = DateTime.now().plusSeconds(62)

        runBlocking {
            NewsRepository.refresh()
        }

        coVerify(exactly = 1) { daoMock.clean() }
    }
}
