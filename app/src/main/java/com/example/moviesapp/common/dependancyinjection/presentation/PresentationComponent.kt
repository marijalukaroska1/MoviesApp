package com.example.moviesapp.common.dependancyinjection.presentation

import com.example.moviesapp.screens.moviedetails.MovieDetailsFragment
import com.example.moviesapp.screens.movieslist.MoviesListActivity
import com.example.moviesapp.screens.movieslist.MoviesListFragment
import dagger.Subcomponent

/**
 * PresentationComponent is a subcomponent of ActivityComponent
 *
 * Whatever you provide in PresentationModule can be used in UseCasesModule and vice versa
 */
@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: MoviesListFragment)
    fun inject(fragment: MovieDetailsFragment)
    fun inject(moviesListActivity: MoviesListActivity) {
    }
}