package com.example.moviedetailsapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getMovies(): Call<APIResponse>

    @GET("discover/movie?")
    fun getMovieByYear(@Query("primary_release_year") query:Int): Call<APIResponse>

    @GET("search/movie?")
    fun searchMovie(@Query("query") query: String): Call<APIResponse>
}