package com.example.moviesapp.common.dependancyinjection.presentation

import com.example.moviesapp.common.dependancyinjection.activity.ActivityComponent
import com.example.moviesapp.screens.moviedetails.MovieDetailsFragment
import com.example.moviesapp.screens.movieslist.MoviesListFragment
import dagger.Component

@PresentationScope
@Component(dependencies = [ActivityComponent::class], modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(fragment: MoviesListFragment)
    fun inject(fragment: MovieDetailsFragment)
}