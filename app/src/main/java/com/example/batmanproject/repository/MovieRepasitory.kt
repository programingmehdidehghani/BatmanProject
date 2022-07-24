package com.example.batmanproject.repository

import com.example.batmanproject.api.RetrofitInstance
import com.example.batmanproject.db.MovieDataBase

class MovieRepasitory(
    val db : MovieDataBase
) {

    suspend fun getBatmanMovie() =
        RetrofitInstance.api.getBatmanMovie()

}