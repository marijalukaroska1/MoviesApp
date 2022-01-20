package com.example.moviesapp.screens.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MoviesRemoteDataSource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * ViewModel is basically just a controller, but a retained controller on configuration changes
 */
class MyViewModel @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : ViewModel() {

    private val _movies = MutableLiveData<PagingData<Movie>>()

    val movies: LiveData<PagingData<Movie>> = _movies

    init {
        //viewModels are retained on configuration changes, therefore when the device is rotated, the activity should be recreated but the viewModel should be reused
        viewModelScope.launch {
            val result = moviesRemoteDataSource.getPopularMovies().collectLatest { moviesList ->
                _movies.value = moviesList
            }
        }
    }
}