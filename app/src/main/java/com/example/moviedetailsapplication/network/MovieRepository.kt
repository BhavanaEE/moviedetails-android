package com.example.moviedetailsapplication.network

import android.util.Log
import com.example.moviedetailsapplication.ui.Movie
import retrofit2.Call

class MovieRepository(val usersInterface: MoviesInterface) {

    fun getMovies(): Call<APIResponse> {
        return usersInterface.getMovies()
    }

    fun convertDTOIntoUIModel(movieResponses: List<MovieResponse>): List<Movie> {
        return movieResponses.map {
            Movie(
                it.id, it.title, it.language, it.releaseDate, it.overView,it.imageUrl,it.voteAverage,it.voteCount)
        }
    }
}