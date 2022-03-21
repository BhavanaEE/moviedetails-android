package com.example.moviedetailsapplication.network

import com.example.moviedetailsapplication.ui.Movie
import retrofit2.Call

class MovieRepository(val usersInterface: MoviesInterface) {

    fun getMovies(): Call<APIResponse> {
        return usersInterface.getMovies()
    }

    fun getCurrentYearMovies(query: Int): Call<APIResponse>{
        return usersInterface.getMovieByYear(query)
    }

    fun getSearchedMovies(query: String): Call<APIResponse>{
        return usersInterface.searchMovie(query)
    }

    fun convertIntoUIModel(movieResponses: List<MovieResponse>): List<Movie> {
        return movieResponses.filter {
            !it.imageUrl.isNullOrEmpty()
        }.map {
            Movie(
                it.id, it.title, it.language, it.releaseDate, it.overView,it.imageUrl,it.voteAverage,it.voteCount)
        }
    }
}