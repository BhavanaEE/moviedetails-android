package com.example.moviedetailsapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetailsapplication.databinding.FragmentPopularBinding
import com.example.moviedetailsapplication.network.MovieRepository
import com.example.moviedetailsapplication.network.RetrofitApi
import com.example.moviedetailsapplication.network.RetrofitService
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MovieViewModel
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MoviesAdapter
import com.example.moviedetailsapplication.retrofitwithrecyclerview.RecyclerItemClickListener
import com.example.moviedetailsapplication.ui.Movie

class PopularFragment:Fragment(R.layout.fragment_popular) {
    private lateinit var binding: FragmentPopularBinding
    private val viewModel: MovieViewModel by lazy { ViewModelProvider(this,
        MovieViewModelFactory( MovieRepository(RetrofitApi.getClient().create(RetrofitService::class.java)))).get(MovieViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popularMovieListRV = binding.popularMovieList
        popularMovieListRV.layoutManager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }

        val movies = ArrayList<Movie>()
        popularMovieListRV.adapter = MoviesAdapter(movies)
        viewModel.getMovies()
        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            popularMovieListRV.adapter = MoviesAdapter(it)
        }
    }
}