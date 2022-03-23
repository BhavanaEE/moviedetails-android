package com.example.moviedetailsapplication.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieDetails(val movieName: String, val overview: String, val imageURL: String): Parcelable