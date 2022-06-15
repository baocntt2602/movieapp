package com.example.movieapplication.ui.home.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.data.models.MovieOverview
import com.example.movieapplication.databinding.ItemMovieOverviewBinding

class MovieOverviewViewHolder(private val binding: ItemMovieOverviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): MovieOverviewViewHolder {
            val binding =
                ItemMovieOverviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MovieOverviewViewHolder(binding)
        }
    }

    fun onBind(item: MovieOverview) {
        Glide.with(binding.root.context)
            .load(item.poster)
            .into(binding.root)
    }
}