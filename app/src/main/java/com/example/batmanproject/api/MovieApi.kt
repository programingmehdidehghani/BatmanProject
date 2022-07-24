package com.example.batmanproject.api

import com.example.batmanproject.Models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("?apikey=3e974fca&s=batman")
    suspend fun getBatmanMovie(
    ) : Response<Movie>

}