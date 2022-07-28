package com.example.batmanproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.batmanproject.Utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.batmanproject.Utils.Resource
import com.example.batmanproject.ViewModel.MovieViewModel
import com.example.batmanproject.ViewModel.NewsViewModelProviderFactory
import com.example.batmanproject.adapter.MovieAdapter
import com.example.batmanproject.db.MovieDataBase
import com.example.batmanproject.repository.MovieRepasitory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository = MovieRepasitory()
        val viewModelProviderFactory = NewsViewModelProviderFactory(application , newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)
        setupRecyclerView()
        Log.i("tag","recycler view")
        viewModel.getBatman()
        viewModel.movieBatman.observe(this@MainActivity,Observer{response ->
            Log.i("tag","view model is call")
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        movieAdapter.differ.submitList(newsResponse.Search)

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(this, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })

    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false


    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }



    private fun setupRecyclerView(){
        movieAdapter = MovieAdapter()
        rvBatmanMovie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}