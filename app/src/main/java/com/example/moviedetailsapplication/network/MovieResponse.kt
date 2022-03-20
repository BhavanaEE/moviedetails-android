package com.example.moviedetailsapplication.network

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val title: String,

    @SerializedName("original_language")
    val language: String,

    @SerializedName("release_date")
    val releaseDate: Date,

    @SerializedName("poster_path")
    val avatar: String,

    @SerializedName("overview")
    val overView: String,

    @SerializedName("backdrop_path")
    val imageUrl:String,

    @SerializedName("vote_average")
    val voteAverage:Double,

    @SerializedName("vote_count")
    val voteCount:Int
)
