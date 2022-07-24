package com.example.batmanproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.batmanproject.ViewModel.MovieViewModel
import com.example.batmanproject.ViewModel.NewsViewModelProviderFactory
import com.example.batmanproject.db.MovieDataBase
import com.example.batmanproject.repository.MovieRepasitory

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository = MovieRepasitory(MovieDataBase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application , newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)
    }
}