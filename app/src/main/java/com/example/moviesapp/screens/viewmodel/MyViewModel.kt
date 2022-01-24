package com.example.moviesapp.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MoviesRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel is basically just a controller, but a retained controller on configuration changes
 */
@HiltViewModel
class MyViewModel @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) : ViewModel() {

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