package com.example.moviesapp.screens

import android.app.Activity
import android.view.View
import androidx.navigation.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.movies.Movie

class ScreensNavigator(private val activity: Activity) {

    fun toMovieDetails(view: View, movie: Movie) {
        view.findNavController()
            .navigate(R.id.action_moviesListFragment_to_movieDetailsFragment)
    }

}