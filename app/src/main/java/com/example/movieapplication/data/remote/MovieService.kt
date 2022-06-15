package com.example.movieapplication.data.remote

import com.example.movieapplication.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    suspend fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("s") search: String,
        @Query("page") page: Int,
    ): MovieResponse
}