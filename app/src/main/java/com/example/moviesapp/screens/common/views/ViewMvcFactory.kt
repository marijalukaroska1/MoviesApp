package com.example.moviesapp.screens.common.views

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviesapp.screens.common.imageloader.ImageLoader
import com.example.moviesapp.screens.moviedetails.MovieDetailsViewMvc
import javax.inject.Inject
import javax.inject.Provider

class ViewMvcFactory @Inject constructor(private val layoutInflaterProvider: Provider<LayoutInflater>, private val imageLoaderProvider: Provider<ImageLoader>) {

    fun getMovieDetailsViewMvc(parent: ViewGroup?): MovieDetailsViewMvc {
        return MovieDetailsViewMvc(layoutInflaterProvider.get(), imageLoaderProvider.get(), parent)
    }
}