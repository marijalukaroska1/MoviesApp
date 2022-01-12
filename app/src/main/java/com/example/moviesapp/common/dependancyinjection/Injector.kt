package com.example.moviesapp.common.dependancyinjection

import com.example.moviesapp.screens.moviedetails.MovieDetailsFragment
import com.example.moviesapp.screens.movieslist.MoviesListFragment

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(fragment: MoviesListFragment) {
        fragment.fetchMoviesUseCase = compositionRoot.fetchMoviesUseCase
        fragment.moviesRemoteDataSource = compositionRoot.moviesRemoteDataSource
        fragment.dialogsNavigator = compositionRoot.dialogsNavigator
        fragment.screensNavigator = compositionRoot.screenNavigator
        fragment.viewMvcFactory = compositionRoot.viewMvcFactory
    }

    fun inject(fragment: MovieDetailsFragment) {
        fragment.dialogsNavigator = compositionRoot.dialogsNavigator
        fragment.fetchMovieDetailsUseCase = compositionRoot.fetchMovieDetailsUseCase
        fragment.viewMvcFactory = compositionRoot.viewMvcFactory
    }
}