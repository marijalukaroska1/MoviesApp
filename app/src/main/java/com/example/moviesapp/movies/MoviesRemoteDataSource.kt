package com.example.moviesapp.movies

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {

    fun getPopularMovies(): Flow<PagingData<Movie>>
}