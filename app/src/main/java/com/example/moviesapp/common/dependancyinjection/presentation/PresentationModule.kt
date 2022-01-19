package com.example.moviesapp.common.dependancyinjection.presentation

import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.networking.MoviesApi
import dagger.Module
import dagger.Provides

/**
 * This class just instantiates other classes and declare dependencies,
 * and does not handle any functional concerns
 */
@Module
class PresentationModule() {

    @Provides
    fun moviesRemoteDataSource(moviesApi: MoviesApi) : MoviesRemoteDataSource =
        FetchMoviesUseCase.MoviesRemoteDataSourceImpl(moviesApi)
}