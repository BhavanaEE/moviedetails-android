package com.example.moviedetailsapplication.retrofitwithrecyclerview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedetailsapplication.network.APIResponse
import com.example.moviedetailsapplication.network.MovieRepository
import com.example.moviedetailsapplication.ui.Movie
import com.example.moviedetailsapplication.ui.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieViewModel: ViewModel() {
    private val _listOfMovies = MutableLiveData(Movies(listOf(Movie(0, "", "", Date(), "", "", 0.0, 0))))
    private val _listOfCurrentYearMovies = MutableLiveData(Movies(listOf(Movie(0, "", "", Date(), "", "", 0.0, 0))))

    val listOfMovies: MutableLiveData<Movies> = _listOfMovies
    val listOfCurrentYearMovies:MutableLiveData<Movies> = _listOfCurrentYearMovies

    fun getMovies(movieRepository: MovieRepository) {
        val movieResponse=movieRepository.getMovies()
        movieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
                Log.v("retrofit", t?.message.toString())
            }

            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfMovies.postValue(Movies(movieRepository.convertDTOIntoUIModel(response!!.body()!!.movies)))
                Log.i("data= ", response.body().toString())
            }
        })
    }

    fun getCurrentYearMovies(movieRepository: MovieRepository) {
        val currentYearMovieResponse=movieRepository.getCurrentYearMovies()
        currentYearMovieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
                Log.v("retrofit", t?.message.toString())
            }

            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfCurrentYearMovies.postValue(Movies(movieRepository.convertDTOIntoUIModel(response!!.body()!!.movies)))
                Log.i("data BHavana= ", response.body().toString())
            }
        })
    }
}