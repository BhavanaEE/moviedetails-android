package com.example.moviedetailsapplication.network

import com.example.moviedetailsapplication.IMAGEURL
import com.example.moviedetailsapplication.ui.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(val retrofitService: RetrofitService) {

    fun getMovies(): Call<APIResponse> {
        return retrofitService.getMovies()
    }

    fun getCurrentYearMovies(query: Int): Call<APIResponse>{
        return retrofitService.getMovieByYear(query)
    }

    fun getSearchedMovies(query: String): Call<APIResponse>{
        return retrofitService.searchMovie(query)
    }

    fun convertIntoUIModel(movieResponses: List<MovieResponse>): List<Movie> {

        return movieResponses.filter {
            !it.imageUrl.isNullOrEmpty()
        }.map {
            val imageUrl= IMAGEURL+it.imageUrl
            Movie(
                it.id, it.title, it.language, it.releaseDate, it.overView,imageUrl,it.voteAverage,it.voteCount)
        }
    }
}