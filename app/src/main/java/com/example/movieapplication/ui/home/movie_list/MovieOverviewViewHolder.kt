package com.example.movieapplication.ui.home.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.movieapplication.R
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
        val glideRO = RequestOptions()
            .error(R.drawable.ic_movie_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_movie_placeholder)

        Glide.with(binding.root.context)
            .load(item.poster)
            .apply(glideRO)
            .into(binding.root)
    }
}