package com.github.mesa_news.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.mesa_news.data.models.New

@Dao
interface NewDao {
    @Insert
    fun insertAll(userExercises: List<New>)

    @Query("DELETE FROM new")
    fun clean()

    @Query("SELECT * FROM new")
    fun getAll(): LiveData<List<New>>

    @Query("SELECT * FROM new WHERE highlight = 1")
    fun getHighlighted(): LiveData<List<New>>

    @Query("SELECT * FROM new WHERE highlight = 0")
    fun getNotHighlighted(): LiveData<List<New>>

    @Update
    fun updateNew(aNew: New)
}
