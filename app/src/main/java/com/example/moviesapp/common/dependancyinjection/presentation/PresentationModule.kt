package com.example.moviesapp.common.dependancyinjection.presentation

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.moviesapp.common.dependancyinjection.activity.ActivityComponent
import com.example.moviesapp.movies.FetchMovieDetailsUseCase
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.networking.MoviesApi
import com.example.moviesapp.screens.common.views.ViewMvcFactory
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import dagger.Module
import dagger.Provides

/**
 * This PresentationCompositionRoot is just one huge factory,
 * and used just to make sure ActivityCompositionRoot stays clean
 *
 * This class just instantiates other classes and declare dependencies, and do not handle any functional concerns
 */
@Module
class PresentationModule(private val activityComponent: ActivityComponent) {

    @Provides
    fun layoutInflater() = activityComponent.layoutInflater()

    @Provides
    fun fragmentManager() = activityComponent.fragmentManager()

    @Provides
    fun moviesApi() = activityComponent.moviesApi()

    @Provides
    fun screenNavigator() = activityComponent.screenNavigator()

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun fetchMoviesUseCase(moviesApi: MoviesApi) = FetchMoviesUseCase(moviesApi)

    @Provides
    fun fetchMovieDetailsUseCase(moviesApi: MoviesApi) = FetchMovieDetailsUseCase(moviesApi)

    @Provides
    fun moviesRemoteDataSource(moviesApi: MoviesApi) : MoviesRemoteDataSource =
        FetchMoviesUseCase.MoviesRemoteDataSourceImpl(moviesApi)

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)
}