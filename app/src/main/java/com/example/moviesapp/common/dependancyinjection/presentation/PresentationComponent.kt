package com.example.moviesapp.common.dependancyinjection.presentation

import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.movies.FetchMovieDetailsUseCase
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.screens.ScreensNavigator
import com.example.moviesapp.screens.common.views.ViewMvcFactory
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun screenNavigator(): ScreensNavigator

    fun dialogsNavigator(): DialogsNavigator

    fun fetchMoviesUseCase(): FetchMoviesUseCase

    fun fetchMovieDetailsUseCase(): FetchMovieDetailsUseCase

    fun moviesRemoteDataSource():
            FetchMoviesUseCase.MoviesRemoteDataSourceImpl

    fun viewMvcFactory(): ViewMvcFactory
}