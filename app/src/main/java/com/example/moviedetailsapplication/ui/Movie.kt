package com.example.moviedetailsapplication.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val releaseDate: String,
    val overView: String,
    val imageUrl:String,
    val voteAverage:Double,
    val voteCount:Int
):Parcelable

