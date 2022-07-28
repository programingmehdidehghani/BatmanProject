package com.example.batmanproject.Models

import androidx.room.Entity
import androidx.room.PrimaryKey



data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)