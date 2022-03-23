package com.example.moviedetailsapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("discover/movie?sort_by=popularity.desc&api_key=e7d826d84097c8429e815cd8ab31c7a0")
    fun getMovies(): Call<APIResponse>

    @GET("discover/movie?primary_release_year=2022&api_key=e7d826d84097c8429e815cd8ab31c7a0")
    fun getMovieByYear(@Query("query") query:Int): Call<APIResponse>

    @GET("search/movie?api_key=e7d826d84097c8429e815cd8ab31c7a0")
    fun searchMovie(@Query("query") query: String): Call<APIResponse>
}