package com.example.movieapplication.di

import com.example.movieapplication.data.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideMovieService(
        retrofit: Retrofit,
    ): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}