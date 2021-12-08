package com.example.moviesapp.screens.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviesapp.R
import com.example.moviesapp.screens.common.BaseViewMvc
import com.example.moviesapp.screens.dialogs.DialogsNavigator

class MovieDetailsViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    dialogsNavigator: DialogsNavigator
) : BaseViewMvc<MovieDetailsViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.layout_movie_details
) {

    interface Listener {

    }
}