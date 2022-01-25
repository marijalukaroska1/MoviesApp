package com.example.moviesapp.screens

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.moviesapp.screens.movieslist.MoviesListFragmentDirections

class ScreensNavigator(private val activity: AppCompatActivity) {

    fun toMovieDetails(view: View, movieId: Int) {
        val action =
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(movieId)
        view.findNavController()
            .navigate(action)
    }
}