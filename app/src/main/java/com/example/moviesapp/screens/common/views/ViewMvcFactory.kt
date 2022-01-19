package com.example.moviesapp.screens.common.views

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import com.example.moviesapp.screens.moviedetails.MovieDetailsViewMvc
import com.example.moviesapp.screens.movieslist.MoviesListViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater) {

    fun getMovieDetailsViewMvc(parent: ViewGroup?): MovieDetailsViewMvc {
        return MovieDetailsViewMvc(layoutInflater, parent)
    }

    fun getMoviesListViewMvc(parent: ViewGroup?, dialogsNavigator: DialogsNavigator): MoviesListViewMvc {
        return MoviesListViewMvc(layoutInflater, parent, dialogsNavigator)
    }
}