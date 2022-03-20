package com.example.moviedetailsapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetailsapplication.network.MovieRepository
import com.example.moviedetailsapplication.network.MoviesInterface
import com.example.moviedetailsapplication.databinding.FragmentPopularBinding
import com.example.moviedetailsapplication.network.RetrofitApi
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MovieViewModel
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MoviesAdapter
import com.example.moviedetailsapplication.ui.Movie
import java.util.ArrayList

class PopularFragment:Fragment(R.layout.fragment_popular) {
    private lateinit var binding: FragmentPopularBinding
    private lateinit var movieRepository: MovieRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        movieRepository = MovieRepository(RetrofitApi.getClient().create(MoviesInterface::class.java))
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.let { ViewModelProvider(it)[MovieViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")

        val popularMovieListRV = binding.popularMovieList
        popularMovieListRV.layoutManager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }

        val movies = ArrayList<Movie>()
        popularMovieListRV.adapter = MoviesAdapter(movies)
        viewModel.getMovies(movieRepository)
        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            popularMovieListRV.adapter = MoviesAdapter(it.listOfMovies)
        }
    }
}