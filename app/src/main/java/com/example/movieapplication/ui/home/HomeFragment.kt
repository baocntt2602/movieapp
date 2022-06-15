package com.example.movieapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieapplication.databinding.FragmentHomeBinding
import com.example.movieapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
    }

    override fun onSubscribeVM() {
        super.onSubscribeVM()
        viewModel.movies.observe(viewLifecycleOwner) {

        }
    }
}