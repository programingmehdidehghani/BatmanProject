package com.example.batmanproject.db

import androidx.room.TypeConverter
import com.example.batmanproject.Models.Movie
import com.example.batmanproject.Models.Search

class Converters {

    @TypeConverter
    fun fromSource(source: Movie) : List<Search>{
        return source.Search
    }

    @TypeConverter
    fun toSource(name : String) : Search {
        return Search(name,name,name,name,name)
    }
}