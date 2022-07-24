package com.example.batmanproject.ViewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.batmanproject.Models.Movie
import com.example.batmanproject.MovieApplication
import com.example.batmanproject.Utils.Resource
import com.example.batmanproject.repository.MovieRepasitory
import kotlinx.coroutines.launch
import okio.IOException

class MovieViewModel(
    app : Application,
    val movieRepasitory : MovieRepasitory
) : AndroidViewModel(app) {

    val movieBatman : MutableLiveData<Resource<Movie>> = MutableLiveData()

    fun getBreakingNews() = viewModelScope.launch {
        safeBreakingNewsCall()

    }

    private suspend fun safeBreakingNewsCall(){
        movieBatman.postValue(Resource.Loading())
        try {
           if (hasInternetConnection()){
              val response = movieRepasitory.getBatmanMovie()
               movieBatman.postValue(hasInternetConnection(response))
           } else {
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