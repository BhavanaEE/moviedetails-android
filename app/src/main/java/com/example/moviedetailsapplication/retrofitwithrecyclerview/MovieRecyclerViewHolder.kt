package com.example.moviedetailsapplication.retrofitwithrecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetailsapplication.R

class MovieRecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    val tvMovieName: TextView = itemView!!.findViewById<TextView>(R.id.movieName)
    val releaseYear: TextView = itemView!!.findViewById<TextView>(R.id.releaseYear)
    val language:TextView = itemView!!.findViewById<TextView>(R.id.language)
//    val overview:TextView = itemView!!.findViewById<TextView>(R.id.overView)
    val image: ImageView = itemView!!.findViewById<AppCompatImageView>(R.id.moviePoster)

}