package com.github.mesa_news.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.mesa_news.data.local.daos.NewDao
import com.github.mesa_news.data.models.New

@Database(entities = [New::class],
    version = 1,
    exportSchema = false)
abstract class DatabaseInterface : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: DatabaseInterface

        fun getDatabase(): DatabaseInterface? {
            synchronized(DatabaseInterface::class.java) {
                if (!::INSTANCE.isInitialized) return null
            }

            return INSTANCE
        }

        fun startDatabase(context: Context) {
            INSTANCE  = Room.databaseBuilder(
                context,
                DatabaseInterface::class.java, "mesa-news"
            ).build()
        }
    }

    abstract fun newDao(): NewDao
}