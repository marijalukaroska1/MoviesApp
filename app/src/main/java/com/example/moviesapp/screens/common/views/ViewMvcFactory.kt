package com.example.moviesapp.screens.common.views

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviesapp.screens.common.imageloader.ImageLoader
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import com.example.moviesapp.screens.moviedetails.MovieDetailsViewMvc
import com.example.moviesapp.screens.movieslist.MoviesListViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater, private val imageLoader: ImageLoader) {

    fun getMovieDetailsViewMvc(parent: ViewGroup?): MovieDetailsViewMvc {
        return MovieDetailsViewMvc(layoutInflater, imageLoader, parent)
    }

    fun getMoviesListViewMvc(parent: ViewGroup?, dialogsNavigator: DialogsNavigator): MoviesListViewMvc {
        return MoviesListViewMvc(layoutInflater, parent, dialogsNavigator)
    }
}