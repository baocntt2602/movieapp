package com.example.movieapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.models.MovieOverview
import com.example.movieapplication.data.models.UIState
import com.example.movieapplication.data.repository.MovieRepository
import com.example.movieapplication.utils.extensions.coSuccessOrThrow
import com.example.movieapplication.utils.extensions.mutated
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movies: LiveData<UIState<List<MovieOverview>>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movies.mutated().postValue(UIState.Loading)
                movieRepository.getMovies("Marvel", 1).coSuccessOrThrow {
                    movies.mutated().postValue(UIState.Success(it))
                }
            } catch (e: Exception) {
                movies.mutated().postValue(UIState.Error(message = e.message.toString()))
            }
        }
    }
}