package com.example.batmanproject.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

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