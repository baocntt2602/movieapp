package com.example.movieapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieapplication.data.models.UIState
import com.example.movieapplication.databinding.FragmentHomeBinding
import com.example.movieapplication.ui.base.BaseFragment
import com.example.movieapplication.ui.home.movie_list.MovieAdapter
import com.example.movieapplication.utils.extensions.onTextChangesDebounce
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val movieAdapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        setUpRV()
        setUpSearchEvent()
    }

    private fun setUpSearchEvent() {
        binding.edtSearch.onTextChangesDebounce {
            binding.rvMovies.unlockLoadMore()
            viewModel.updateQuery(it.trim())
        }
    }

    private fun setUpRV() {
        binding.rvMovies.apply {
            loadMoreInvoker = {
                viewModel.getMoreMovies()
            }
            adapter = movieAdapter
        }
    }

    override fun onSubscribeVM() {
        super.onSubscribeVM()
        viewModel.movies.observe(viewLifecycleOwner) { data ->
            movieAdapter.submitList(data)
        }

        viewModel.firstLoad.observe(this) {
            handleLoading(it is UIState.Loading)
        }

        viewModel.loadingMore.observe(this) {
            if (it is UIState.Loading) {
                binding.rvMovies.showLoadMoreIndicator()
            } else {
                binding.rvMovies.hideLoadMoreIndicator()
            }
        }
    }
}