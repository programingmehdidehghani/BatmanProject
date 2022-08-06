package com.example.batmanproject.ViewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.batmanproject.Models.DetailMovie.DetailMovie
import com.example.batmanproject.Models.Movie
import com.example.batmanproject.Models.Search
import com.example.batmanproject.MovieApplication
import com.example.batmanproject.Utils.Resource
import com.example.batmanproject.repository.MovieRepasitory
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class MovieViewModel(
    app : Application,
    val movieRepasitory : MovieRepasitory
) : AndroidViewModel(app) {

    val movieBatman : MutableLiveData<Resource<Movie>> = MutableLiveData()
    val movieBatmanOffline : MutableLiveData<LiveData<List<Search>>> = MutableLiveData()
    var breakingMoviePage = 1
    var breakingMovieResponse: Movie? = null

    val detailMovie : MutableLiveData<Resource<DetailMovie>> = MutableLiveData()
    var detailMoviePage = 1
    var detailMovieResponse: DetailMovie? = null



    fun getBatman() = viewModelScope.launch {
        safeBreakingNewsCall()
    }

    fun getDetailMovie(select: String) = viewModelScope.launch {
        getSelectMovie(select)
    }

    fun saveMovie(search: MutableList<Search>) = viewModelScope.launch {
        movieRepasitory.insertDb(search)
    }

    fun getAllMovie() : LiveData<List<Search>> {
        return movieRepasitory.getMovieFromDd()
    }

    private suspend fun getSelectMovie(select: String) {
        detailMovie.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()){
                val response = movieRepasitory.getDetailMovie(select)
                detailMovie.postValue(handleDetailResponse(response))
            } else {
                var items = getAllMovie()
                detailMovie.postValue(Resource.Error("no internet connection"))
            }
        } catch (T:Throwable){
            when(T){
                is IOException -> detailMovie.postValue(Resource.Error("internet is failure"))
                else -> detailMovie.postValue(Resource.Error("conversion error"))
            }
        }
    }

    private fun handleBreakingNewsResponse(response: Response<Movie>) : Resource<Movie>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                breakingMoviePage++
                if (breakingMovieResponse == null){
                    breakingMovieResponse = resultResponse
                }else {
                    val oldArticle = breakingMovieResponse?.Search
                    val newArticle = resultResponse.Search
                    oldArticle?.addAll(newArticle)
                }
                return Resource.Success(breakingMovieResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

/*    private fun getOfflineData(item : LiveData<List<Search>>){
          breakingSearch =  item

        return Resource.Success(breakingSearch ?: itemm)
    }*/


    private fun handleDetailResponse(response: Response<DetailMovie>) : Resource<DetailMovie>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                detailMoviePage++
                if (detailMovieResponse == null){
                    detailMovieResponse = resultResponse
                }else {
                    val oldArticle = detailMovieResponse
                    val newArticle = resultResponse
                }
                return Resource.Success(detailMovieResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private suspend fun safeBreakingNewsCall(){
        movieBatman.postValue(Resource.Loading())
        try {
           if (hasInternetConnection()){
              val response = movieRepasitory.getBatmanMovie()
               movieBatman.postValue(handleBreakingNewsResponse(response))
           } else {
               movieBatmanOffline.postValue(getAllMovie())
               movieBatman.postValue(Resource.Error("no internet connection"))

           }
        } catch (T:Throwable){
            when(T){
                is IOException -> movieBatman.postValue(Resource.Error("internet is failure"))
                else -> movieBatman.postValue(Resource.Error("conversion error"))
            }
        }
    }

    private fun hasInternetConnection () : Boolean {
        val connectivityManager = getApplication<MovieApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    NetworkCapabilities.TRANSPORT_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }



}