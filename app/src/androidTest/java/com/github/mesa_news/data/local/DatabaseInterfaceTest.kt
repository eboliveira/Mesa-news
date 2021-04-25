package com.github.mesa_news.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.github.mesa_news.data.local.daos.NewDao
import com.github.mesa_news.data.models.New
import getOrAwaitValue
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@MediumTest
class DatabaseInterfaceTest {
    @Rule
    @JvmField
    var executor = InstantTaskExecutorRule()

    companion object {
        private lateinit var db: DatabaseInterface
        private lateinit var newDao: NewDao

        @BeforeClass
        @JvmStatic
        fun createDb() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            db = Room.inMemoryDatabaseBuilder(
                context, DatabaseInterface::class.java
            ).build()
            newDao = db.newDao()
        }

        @AfterClass
        @JvmStatic
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun writeNewsAndReadInList() {
        val news = NewFactory.getList(3)
        newDao.insertAll(news)

        var newsRoom = newDao.getAll()

        newsRoom.getOrAwaitValue()
        assertThat(news.size, equalTo(newsRoom.value?.size))

        newDao.clean()

        newsRoom = newDao.getAll()
        newsRoom.getOrAwaitValue()
        assert(newsRoom.value?.isEmpty() ?: false)
    }

    @Test
    @Throws(Exception::class)
    fun writeHighlightedNewsAndReadInList() {
        val new = New(highlight = true)
        newDao.insertAll(listOf(new))

        val newsRoom = newDao.getHighlighted()

        newsRoom.getOrAwaitValue()
        assertThat(1, equalTo(newsRoom.value?.size))

        newDao.clean()
    }
}
