package com.example.moviedetailsapplication.retrofitwithrecyclerview

import android.content.Intent
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

    override fun onBindViewHolder(holder: MovieRecyclerViewHolder, position: Int) {

        holder.tvMovieName.text = movies.get(position).title
        holder.releaseYear.text = movies.get(position).releaseDate.toString()
        holder.language.text=movies.get(position).originalLanguage
        val imageUrl="https://image.tmdb.org/t/p/w200"+movies.get(position).imageUrl
        Glide.with(holder.itemView.context).load(imageUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
        holder.itemView.setOnClickListener {

            val intent = Intent(it.context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIENAME, holder.tvMovieName.text.toString())
            intent.putExtra(OVERVIEW,movies.get(position).overView)
            intent.putExtra(IMAGEURL,movies.get(position).imageUrl)
            it.context.startActivity(intent);
        }
    }
}