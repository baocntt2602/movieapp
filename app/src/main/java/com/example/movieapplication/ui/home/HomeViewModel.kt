package com.example.movieapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.models.MovieOverview
import com.example.movieapplication.data.models.UIState
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.utils.extensions.SingleLiveEvent
import com.example.movieapplication.utils.extensions.coSuccessOrThrow
import com.example.movieapplication.utils.extensions.mutated
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movies: LiveData<List<MovieOverview>> = MutableLiveData()
    val firstLoad: LiveData<UIState<Unit>> = SingleLiveEvent()
    val loadingMore: LiveData<UIState<Unit>> = SingleLiveEvent()

    private var query = MovieQuery()
    private var canLoadMore = true

    companion object {
        private const val SIZE_PER_PAGE = 10
    }

    fun getMovies() {
        reset()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                firstLoad.mutated().postValue(UIState.Loading)
                movieRepository.getMovies(query.search, query.page).coSuccessOrThrow {
                    canLoadMore = it.size >= SIZE_PER_PAGE
                    generateMovieList(false, it)
                    firstLoad.mutated().postValue(UIState.Success(Unit))
                }
            } catch (e: Throwable) {
                firstLoad.mutated().postValue(UIState.Error(message = e.message.toString()))
                canLoadMore = false
            }
        }
    }

    fun getMoreMovies() {
        if (!canLoadMore) {
            loadingMore.mutated().postValue(UIState.Error(message = "Cant load more"))
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loadingMore.mutated().postValue(UIState.Loading)
                query.page++
                movieRepository.getMovies(
                    query.search, query.page
                ).coSuccessOrThrow {
                    canLoadMore = it.size >= SIZE_PER_PAGE
                    loadingMore.mutated().postValue(UIState.Success(Unit))
                    generateMovieList(true, it)
                }
            } catch (e: Throwable) {
                canLoadMore = false
                loadingMore.mutated()
                    .postValue(UIState.Error(message = e.message ?: "Unexpected error"))
            }
        }
    }

    fun updateQuery(search: String) {
        if (search.isNotEmpty()) {
            query.search = search
        } else {
            query.search = "Marvel"
        }
        getMovies()
    }

    private fun generateMovieList(isLoadMore: Boolean, list: List<MovieOverview>) {
        if (isLoadMore) {
            val newList = movies.mutated().value.orEmpty().toMutableList().apply {
                addAll(list)
            }
            movies.mutated().postValue(newList)
        } else {
            movies.mutated().postValue(list)
        }
    }

    private fun reset() {
        query.page = 1
        canLoadMore = true
    }
}

data class MovieQuery(var search: String = "Marvel", var page: Int = 1)