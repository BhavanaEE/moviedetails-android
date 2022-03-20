package com.example.moviedetailsapplication.ui

import java.util.*

data class Movie (
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val releaseDate: Date,
    val overView: String,
    val imageUrl:String,
    val voteAverage:Double,
    val voteCount:Int
)

