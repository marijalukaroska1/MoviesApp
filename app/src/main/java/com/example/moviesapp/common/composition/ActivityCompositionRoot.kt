package com.example.moviesapp.common.composition

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.screens.ScreensNavigator
import com.example.moviesapp.screens.dialogs.DialogsNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    private val fragmentManager get() = activity.supportFragmentManager

    val dialogsNavigator get() = DialogsNavigator(fragmentManager)

    val screenNavigator get() = ScreensNavigator(activity)

    val moviesApi get() = appCompositionRoot.moviesApi

    val fetchMoviesUseCase get() = FetchMoviesUseCase(moviesApi)
}