package com.example.batmanproject.repository

import com.example.batmanproject.Models.Search
import com.example.batmanproject.api.RetrofitInstance
import com.example.batmanproject.db.MovieDataBase

class MovieRepasitory(
    val db : MovieDataBase
) {

    suspend fun getBatmanMovie() =
        RetrofitInstance.api.getBatmanMovie()

//    suspend fun getDetailMovie(imdbID : String) =
//        RetrofitInstance.api.getDetailMovie(imdbID)

    suspend fun insertDb(search: MutableList<Search>) {
        var searchInsert : Search
        for (item in search){
            searchInsert = item
            db.getArticleDao().upsert(searchInsert)
        }
    }



    fun getMovieFromDd() = db.getArticleDao().getAllArticles()


}