package com.example.moviesapp.common.dependancyinjection.presentation

import com.example.moviesapp.screens.moviedetails.MovieDetailsFragment
import com.example.moviesapp.screens.movieslist.MoviesListFragment
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: MoviesListFragment)
    fun inject(fragment: MovieDetailsFragment)
}