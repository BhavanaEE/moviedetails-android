package com.example.moviedetailsapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviedetailsapplication.ui.Movie

class MovieDetailsActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_description)
        val movieDetails = intent.extras!!.getParcelable<Movie>(MOVIEDETAILS)
        val imageViewTv = findViewById<ImageView>(R.id.imageTv)
        val imageURL=movieDetails!!.imageUrl
        var outputString = getString(R.string.displayMessage,movieDetails.title, movieDetails.overView)
        Glide.with(this).load(imageURL).into(imageViewTv)
        findViewById<TextView>(R.id.movieOverview).setText(outputString)

    }
}