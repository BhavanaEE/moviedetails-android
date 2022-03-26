package com.example.moviedetailsapplication.retrofitwithrecyclerview

import androidx.lifecycle.LiveData
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

class MovieViewModel(val movieRepository: MovieRepository): ViewModel() {
    private val _listOfMovies = MutableLiveData<List<Movie>>()
    private val _listOfCurrentYearMovies = MutableLiveData<List<Movie>>()
    private val _listOfSearchedMovies = MutableLiveData<List<Movie>>()

    val listOfMovies: LiveData<List<Movie>> = _listOfMovies
    val listOfCurrentYearMovies:LiveData<List<Movie>> = _listOfCurrentYearMovies
    val listOfSearchedMovies:LiveData<List<Movie>> = _listOfSearchedMovies

    fun getMovies() {
        val movieResponse=movieRepository.getMovies()
        movieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfMovies.postValue(movieRepository.convertIntoUIModel(response!!.body()!!.movies))
            }
        })
    }

    fun getCurrentYearMovies() {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val currentYearMovieResponse=movieRepository.getCurrentYearMovies(year)
        currentYearMovieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfCurrentYearMovies.postValue(movieRepository.convertIntoUIModel(response!!.body()!!.movies))
            }
        })
    }

    fun getListOfSearchedMovies(query: String){
        val currentYearMovieResponse=movieRepository.getSearchedMovies(query)
        currentYearMovieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfSearchedMovies.postValue(movieRepository.convertIntoUIModel(response!!.body()!!.movies))
            }
        })
    }
}