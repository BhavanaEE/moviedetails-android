package com.example.moviedetailsapplication.network

import retrofit2.Call
import retrofit2.http.GET

interface MoviesInterface {

    @GET("movie?sort_by=popularity.desc&api_key=e7d826d84097c8429e815cd8ab31c7a0")
    fun getMovies(): Call<APIResponse>
}