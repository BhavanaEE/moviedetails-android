package com.example.moviedetailsapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MovieDetailsActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        val movieNameTv = intent.extras?.getString("movieName")
        val overViewTv = intent.extras?.getString("overview")
        var outputString = getString(R.string.displayMessage,movieNameTv, overViewTv)
        findViewById<TextView>(R.id.movieOverview).setText(outputString)

    }
}