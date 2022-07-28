package com.example.batmanproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.batmanproject.Models.Movie
import com.example.batmanproject.Models.Search


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(search: Search) : Long

    @Query("SELECT * FROM searches")
    fun getAllArticles() : LiveData<List<Search>>

}
