package com.example.moviesapp.screens

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.moviesapp.common.dependancyinjection.activity.ActivityScope
import com.example.moviesapp.screens.movieslist.MoviesListFragmentDirections
import javax.inject.Inject

@ActivityScope
class ScreensNavigator @Inject constructor(private val activity: AppCompatActivity) {

    fun toMovieDetails(view: View, movieId: Int) {
        val action =
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(movieId)
        view.findNavController()
            .navigate(action)
    }

}