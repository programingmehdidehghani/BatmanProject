package com.example.batmanproject.Models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "movies"
)
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    val Response: String,
    val Search: MutableList<Search>,
    val totalResults: String
)