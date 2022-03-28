package com.example.moviedetailsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetailsapplication.databinding.ActivitySearchBinding
import com.example.moviedetailsapplication.network.MovieRepository
import com.example.moviedetailsapplication.network.RetrofitService
import com.example.moviedetailsapplication.network.RetrofitApi
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MovieViewModel
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MoviesAdapter
import com.example.moviedetailsapplication.ui.Movie
import java.util.ArrayList

class SearchActivity:AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: MovieViewModel by lazy { ViewModelProvider(this,
        MovieViewModelFactory(MovieRepository(RetrofitApi.getClient().create(RetrofitService::class.java)))).get(MovieViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        binding = ActivitySearchBinding.inflate(layoutInflater)

        val searchBarRV = findViewById<RecyclerView>(R.id.searchBarRecyclerView)
        val searchBar = findViewById<SearchView>(R.id.searchBar)
        searchBarRV.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val movies = ArrayList<Movie>()
        searchBarRV.adapter = MoviesAdapter(movies)

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getListOfSearchedMovies(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        viewModel.listOfSearchedMovies.observe(this) {
            searchBarRV.adapter = MoviesAdapter(it.moviesList)
        }
    }
}