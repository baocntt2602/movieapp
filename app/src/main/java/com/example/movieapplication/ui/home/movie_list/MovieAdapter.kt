package com.example.movieapplication.ui.home.movie_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapplication.data.models.MovieOverview

class MovieAdapter : ListAdapter<MovieOverview, MovieOverviewViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieOverview>() {
            override fun areItemsTheSame(oldItem: MovieOverview, newItem: MovieOverview): Boolean =
                oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(
                oldItem: MovieOverview,
                newItem: MovieOverview
            ): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieOverviewViewHolder {
        return MovieOverviewViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieOverviewViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}