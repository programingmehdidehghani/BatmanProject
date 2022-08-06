package com.example.batmanproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batmanproject.Models.Search
import com.example.batmanproject.Utils.Resource
import com.example.batmanproject.ViewModel.MovieViewModel
import com.example.batmanproject.ViewModel.NewsViewModelProviderFactory
import com.example.batmanproject.adapter.MovieAdapter
import com.example.batmanproject.adapter.SelectAdapter
import com.example.batmanproject.db.MovieDataBase
import com.example.batmanproject.repository.MovieRepasitory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieAdapter
    lateinit var selectAdapter: SelectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository = MovieRepasitory(MovieDataBase(this))
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
                        rvBatmanMovie.visibility = View.VISIBLE
                        movieAdapter.differ.submitList(newsResponse.Search)
                        viewModel.saveMovie(newsResponse.Search)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        viewModel.getAllMovie().observe(this,Observer{ response ->
                            paginationProgressBar.visibility = View.INVISIBLE
                            rvBatmanMovie.visibility = View.VISIBLE
                            movieAdapter.differ.submitList(response)
                        })
                        Toast.makeText(this, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })

        movieAdapter.setOnItemClickListener { search ->
            viewModel.getDetailMovie(search.imdbID)
            viewModel.detailMovie.observe(this@MainActivity,Observer{ response ->
                Log.i("tag","view model is call")
                when(response){
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            rvBatmanMovie.visibility = View.GONE
                            setupAdapterSelect()
                            selectAdapter.differ.submitList(newsResponse)
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

    private fun setupAdapterSelect(){
        selectAdapter = SelectAdapter()
        rvSelectMovie.apply {
            adapter = selectAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}

private fun <T> AsyncListDiffer<T>.submitList(newsData: LiveData<List<T>>) {


}


