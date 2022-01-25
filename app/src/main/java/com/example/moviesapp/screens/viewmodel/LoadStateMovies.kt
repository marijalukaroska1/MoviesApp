package com.example.moviesapp.screens.viewmodel

import androidx.paging.PagingData
import com.example.moviesapp.movies.Movie
import kotlinx.coroutines.flow.Flow

sealed class LoadStateMovies {
    data class Success(val movies: Flow<PagingData<Movie>>) : LoadStateMovies()
    class Error(val errorMessage: String) : LoadStateMovies()
    object Loading : LoadStateMovies()
}