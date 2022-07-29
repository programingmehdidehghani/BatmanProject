package com.example.batmanproject.Utils

import androidx.lifecycle.LiveData
import com.example.batmanproject.Models.Search

sealed class Resource<T>(
    val data : T? = null,
    val message : String? = null,
){
    class Success<T>(data: T) : Resource<T> (data)
    class Error<T>(message: String , data: T? = null) : Resource<T>(data , message)
    class Loading<T> : Resource<T>()
}
