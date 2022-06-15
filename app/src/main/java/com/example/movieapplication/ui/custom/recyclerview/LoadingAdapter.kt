package com.example.movieapplication.ui.custom.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.databinding.ItemLoadingBinding

class LoadingAdapter : ListAdapter<Unit, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var flag: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                if (value) {
                    submitList(listOf(Unit))
                } else {
                    submitList(emptyList())
                }
            }
        }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Unit>() {
            override fun areItemsTheSame(oldItem: Unit, newItem: Unit): Boolean = false

            override fun areContentsTheSame(oldItem: Unit, newItem: Unit): Boolean = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LoadingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}

class LoadingViewHolder(binding: ItemLoadingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): LoadingViewHolder {
            val binding =
                ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val rootLp = binding.root.layoutParams
            if (rootLp is GridLayoutManager.LayoutParams) {
                rootLp.width = parent.measuredWidth
                binding.root.layoutParams = rootLp
            }
            return LoadingViewHolder(binding)
        }
    }
}

