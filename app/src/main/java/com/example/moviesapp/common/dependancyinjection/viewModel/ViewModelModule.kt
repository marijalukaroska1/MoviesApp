package com.example.moviesapp.common.dependancyinjection.viewModel

import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.MoviesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun moviesRemoteDataSource(moviesRemoteDataSourceImpl: FetchMoviesUseCase.MoviesRemoteDataSourceImpl) : MoviesRemoteDataSource
}