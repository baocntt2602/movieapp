package com.example.movieapplication.data.repository

import com.example.movieapplication.BuildConfig
import com.example.movieapplication.data.models.ApiResponse
import com.example.movieapplication.data.models.MovieOverview
import com.example.movieapplication.data.remote.MovieService
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getMovies(
        search: String,
        page: Int
    ): ApiResponse<List<MovieOverview>> {
        return try {
            val movieOverviews =
                movieService.getMovies(BuildConfig.API_KEY, search, page).movieOverviews
                    ?: emptyList()
            ApiResponse.Success(movieOverviews)
        } catch (e: Exception) {
            ApiResponse.ApiFailure(e.message.toString())
        }
    }
}