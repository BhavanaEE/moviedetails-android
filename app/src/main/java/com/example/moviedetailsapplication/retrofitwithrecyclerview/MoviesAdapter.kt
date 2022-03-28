package com.example.moviedetailsapplication.retrofitwithrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedetailsapplication.*
import com.example.moviedetailsapplication.ui.Movie

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieRecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecyclerViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter,parent,false)
        return MovieRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(movieRecyclerViewHolder: MovieRecyclerViewHolder, position: Int) {
        movieRecyclerViewHolder.tvMovieName.text = movies.get(position).title
        movieRecyclerViewHolder.releaseYear.text = movies.get(position).releaseDate
        movieRecyclerViewHolder.language.text=movies.get(position).originalLanguage
        val imageUrl= movies.get(position).imageUrl
        Glide.with(movieRecyclerViewHolder.itemView.context).load(imageUrl)
            .apply(RequestOptions().centerCrop())
            .into(movieRecyclerViewHolder.image)
    }
}