package com.github.mesa_news.ui.news

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.github.mesa_news.R
import com.github.mesa_news.data.models.New
import com.github.mesa_news.data.repositories.NewsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsFragmentTest {
    @Before
    fun setUpMocks() {
        mockkObject(NewsRepository)
    }

    @Test
    fun launchFragmentAndCheckRecyclerViewSize() {
        val news = MutableLiveData(listOf(mockk<New>(relaxed = true)))
        setHighlightedNewsRepositoryMock(news)

        with(launchFragmentInContainer<NewsFragment>()) {
            moveToState(Lifecycle.State.RESUMED)

            onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
            onView(withId(R.id.recycler_view)).check { view, _ ->
                check((view as RecyclerView).adapter?.itemCount == 1)
            }
        }
    }

    @Test
    fun launchFragmentAndCheckItemTitle() {
        val news = NewFactory.getList(2)
        setHighlightedNewsRepositoryMock(MutableLiveData(news))

        with(launchFragmentInContainer<NewsFragment>()) {
            moveToState(Lifecycle.State.RESUMED)

            onView(withText(news[0].title)).check(matches(isDisplayed()))
            onView(withText(news[1].title)).check(matches(isDisplayed()))
        }
    }

    private fun setHighlightedNewsRepositoryMock(news: MutableLiveData<List<New>>) {
        every { NewsRepository.highlightedNews } returns news
    }
}
