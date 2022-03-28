package com.example.moviedetailsapplication.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {

    var BASE_URL = "https://api.themoviedb.org/3/"

    fun getClient(): Retrofit {
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(APIInterceptor())
            .build()

        val retrofit = Retrofit.Builder().client(builder)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit
    }
}