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

class MovieViewModel: ViewModel() {
    private val _listOfMovies = MutableLiveData(Movies(listOf(Movie(0, "", "", "", "", "", 0.0, 0))))
    private val _listOfCurrentYearMovies = MutableLiveData(Movies(listOf(Movie(0, "", "", "", "", "", 0.0, 0))))
    private val _listOfSearchedMovies = MutableLiveData(Movies(listOf(Movie(0, "", "", "", "", "", 0.0, 0))))

    val listOfMovies: LiveData<Movies> = _listOfMovies
    val listOfCurrentYearMovies:LiveData<Movies> = _listOfCurrentYearMovies
    val listOfSearchedMovies:LiveData<Movies> = _listOfSearchedMovies

    fun getMovies(movieRepository: MovieRepository) {
        val movieResponse=movieRepository.getMovies()
        movieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfMovies.postValue(Movies(movieRepository.convertIntoUIModel(response!!.body()!!.movies)))
            }
        })
    }

    fun getCurrentYearMovies(movieRepository: MovieRepository) {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val currentYearMovieResponse=movieRepository.getCurrentYearMovies(year)
        currentYearMovieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfCurrentYearMovies.postValue(Movies(movieRepository.convertIntoUIModel(response!!.body()!!.movies)))
            }
        })
    }

    fun getListOfSearchedMovies(movieRepository: MovieRepository,query: String){
        val currentYearMovieResponse=movieRepository.getSearchedMovies(query)
        currentYearMovieResponse.enqueue(object : Callback<APIResponse> {
            override fun onFailure(call: Call<APIResponse>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<APIResponse>?, response: Response<APIResponse>?) {
                _listOfSearchedMovies.postValue(Movies(movieRepository.convertIntoUIModel(response!!.body()!!.movies)))
            }
        })
    }
}