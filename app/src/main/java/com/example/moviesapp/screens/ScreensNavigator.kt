package com.example.moviesapp.screens

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.moviesapp.screens.movieslist.MoviesListFragmentDirections
import com.example.moviesapp.screens.viewmodel.ViewModelActivity

class ScreensNavigator(private val activity: AppCompatActivity) {

    fun toMovieDetails(view: View, movieId: Int) {
        val action =
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(movieId)
        view.findNavController()
            .navigate(action)
    }

    fun toViewModelScreen(context : Context) {
        activity.startActivity(Intent(context, ViewModelActivity::class.java))
    }

}