package com.example.movieapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieapplication.data.models.UIState
import com.example.movieapplication.databinding.FragmentHomeBinding
import com.example.movieapplication.ui.base.BaseFragment
import com.example.movieapplication.ui.home.movie_list.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val movieAdapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        setUpRV()
    }

    private fun setUpRV() {
        binding.rvMovies.adapter = movieAdapter
    }

    override fun onSubscribeVM() {
        super.onSubscribeVM()
        viewModel.movies.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    movieAdapter.submitList(state.data)
                }
                else -> {

                }
            }
        }
    }
}