package com.example.moviesapp.common.dependancyinjection.presentation

import com.example.moviesapp.movies.FetchMovieDetailsUseCase
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.networking.MoviesApi
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun fetchMoviesUseCase(moviesApi: MoviesApi) = FetchMoviesUseCase(moviesApi)

    @Provides
    fun fetchMovieDetailsUseCase(moviesApi: MoviesApi) = FetchMovieDetailsUseCase(moviesApi)
}