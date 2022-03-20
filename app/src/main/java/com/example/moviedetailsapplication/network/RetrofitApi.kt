package com.example.moviedetailsapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {

    var BASE_URL = "https://api.themoviedb.org/3/discover/"

    fun getClient(): Retrofit {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit
    }
}