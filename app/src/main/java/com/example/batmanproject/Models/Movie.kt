package com.example.batmanproject.Models

import androidx.room.Entity



data class Movie(
    val Response: String,
    val Search: MutableList<Search>,
    val totalResults: String
)