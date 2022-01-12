package com.example.moviesapp.common.dependancyinjection

import com.example.moviesapp.movies.FetchMovieDetailsUseCase
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.screens.common.views.ViewMvcFactory
import com.example.moviesapp.screens.dialogs.DialogsNavigator

/**
 * This PresentationCompositionRoot is just one huge factory,
 * and used just to make sure ActivityCompositionRoot stays clean
 *
 * This class just instantiates other classes and declare dependencies, and do not handle any functional concerns
 */
class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    private val layoutInflater get() = activityCompositionRoot.layoutInflater

    private val fragmentManager get() = activityCompositionRoot.fragmentManager

    private val moviesApi get() = activityCompositionRoot.moviesApi

    val screenNavigator get() = activityCompositionRoot.screenNavigator

    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    //delegate
    //get() only when its actually required
    val fetchMoviesUseCase get() = FetchMoviesUseCase(moviesApi)

    val fetchMovieDetailsUseCase get() = FetchMovieDetailsUseCase(moviesApi)

    val moviesRemoteDataSource get() = FetchMoviesUseCase.MoviesRemoteDataSourceImpl(moviesApi)

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
}