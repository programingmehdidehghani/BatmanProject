package com.example.batmanproject.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.batmanproject.repository.MovieRepasitory

class NewsViewModelProviderFactory (
val app: Application,
val movieRepasitory: MovieRepasitory
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(app , movieRepasitory) as T
    }
}