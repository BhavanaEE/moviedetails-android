package com.example.moviedetailsapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedetailsapplication.databinding.FragmentCurrentYearBinding
import com.example.moviedetailsapplication.network.MovieRepository
import com.example.moviedetailsapplication.network.RetrofitService
import com.example.moviedetailsapplication.network.RetrofitApi
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MovieViewModel
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MoviesAdapter
import com.example.moviedetailsapplication.retrofitwithrecyclerview.RecyclerItemClickListener
import com.example.moviedetailsapplication.ui.Movie
import java.util.ArrayList

class CurrentYearFragment:Fragment(R.layout.fragment_current_year) {
    private lateinit var binding: FragmentCurrentYearBinding
    private val viewModel: MovieViewModel by lazy { ViewModelProvider(this,
        MovieViewModelFactory( MovieRepository(RetrofitApi.getClient().create(RetrofitService::class.java)))).get(MovieViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentYearBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentYearMovieListRV = binding.currentYearMoviesList
        currentYearMovieListRV.layoutManager =
            LinearLayoutManager(activity).apply { orientation = LinearLayoutManager.VERTICAL }

        val movies = ArrayList<Movie>()
        currentYearMovieListRV.adapter = MoviesAdapter(movies)
        viewModel.getCurrentYearMovies()
        viewModel.listOfCurrentYearMovies.observe(viewLifecycleOwner) {
            currentYearMovieListRV.adapter = MoviesAdapter(it)
        }

        currentYearMovieListRV.addOnItemTouchListener(
            RecyclerItemClickListener(context, currentYearMovieListRV,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val intent= Intent(requireContext(),MovieDetailsActivity::class.java)
                        intent.putExtra(MOVIEDETAILS, viewModel.listOfCurrentYearMovies.value?.get(position))
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                    }
                })
        )
    }
}