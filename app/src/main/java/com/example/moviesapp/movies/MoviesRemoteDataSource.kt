package com.example.moviesapp.movies

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MoviesRemoteDataSource {

    fun getPopularMovies(): Flow<PagingData<Movie>>
}