package com.example.moviesapp.common.dependancyinjection.presentation

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.networking.MoviesApi
import com.example.moviesapp.screens.common.views.ViewMvcFactory
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import dagger.Module
import dagger.Provides

/**
 * This class just instantiates other classes and declare dependencies,
 * and does not handle any functional concerns
 */
@Module
class PresentationModule() {

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun moviesRemoteDataSource(moviesApi: MoviesApi) : MoviesRemoteDataSource =
        FetchMoviesUseCase.MoviesRemoteDataSourceImpl(moviesApi)

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)
}