package com.example.batmanproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.batmanproject.Models.Movie
import com.example.batmanproject.Models.Search


@Database(
    entities = [Search::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun getArticleDao() : MovieDao

    companion object{
        @Volatile
        private var instance : MovieDataBase ?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDataBase::class.java,
                "article_db.db"
            ).build()

    }
}