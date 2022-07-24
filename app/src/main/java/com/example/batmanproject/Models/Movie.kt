package com.example.batmanproject.Models

data class Movie(
    val Response: String,
    val Search: MutableList<Search>,
    val totalResults: String
)