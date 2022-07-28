package com.example.batmanproject.Models

import androidx.room.Entity
import androidx.room.PrimaryKey



data class Movie(

    val Response: String,
    val Search: MutableList<Search>,
    val totalResults: String
)