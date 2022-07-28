package com.example.batmanproject.repository

import com.example.batmanproject.Models.Movie
import com.example.batmanproject.api.RetrofitInstance
import com.example.batmanproject.db.MovieDataBase

class MovieRepasitory(
    //val db : MovieDataBase
) {

    suspend fun getBatmanMovie() =
        RetrofitInstance.api.getBatmanMovie()

//    suspend fun getDetailMovie(imdbID : String) =
//        RetrofitInstance.api.getDetailMovie(imdbID)

/*
    suspend fun insertDb(movie: Movie) = db.getArticleDao().upsert(movie)
*/


}