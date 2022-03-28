package com.example.moviedetailsapplication.network

import com.example.moviedetailsapplication.APIKEY
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class APIInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val newUrl = request.url().newBuilder().addQueryParameter("api_key", APIKEY).build()
        request = request.newBuilder().url(newUrl).build()
        val response: Response = chain.proceed(request)
        return response
    }
}