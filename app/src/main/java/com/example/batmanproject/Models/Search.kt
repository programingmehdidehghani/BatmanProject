package com.example.batmanproject.Models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "searches"
)
data class Search(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)