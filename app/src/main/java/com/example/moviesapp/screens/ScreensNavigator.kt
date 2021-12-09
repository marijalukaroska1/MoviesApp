package com.example.moviesapp.screens

import android.app.Activity
import android.view.View
import androidx.navigation.findNavController
import com.example.moviesapp.screens.movieslist.MoviesListFragmentDirections

class ScreensNavigator(private val activity: Activity) {

    fun toMovieDetails(view: View, movieId: Int) {
        val action =
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(movieId)
        view.findNavController()
            .navigate(action)
    }

}