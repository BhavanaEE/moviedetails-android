package com.example.moviedetailsapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailsActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        val movieName = intent.extras?.getString(MOVIENAME)
        val overView = intent.extras?.getString(OVERVIEW)
        val image = intent.extras?.getString(IMAGEURL)
        val imageViewTv = findViewById<ImageView>(R.id.imageTv)
        val imageURL="https://image.tmdb.org/t/p/w200"+image
        var outputString = getString(R.string.displayMessage,movieName, overView)
        Glide.with(this).load(imageURL).into(imageViewTv)
        findViewById<TextView>(R.id.movieOverview).setText(outputString)

    }
}