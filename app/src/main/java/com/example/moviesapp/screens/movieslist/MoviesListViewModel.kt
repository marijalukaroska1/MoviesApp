package com.example.moviesapp.screens.movieslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.screens.viewmodel.LoadStateMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) :
    ViewModel() {

    private val _movies = MutableStateFlow<LoadStateMovies>(LoadStateMovies.Loading)
    val movies = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _movies.value = LoadStateMovies.Success(getMovies())
            } catch (e: Exception) {
                Log.e(this::class.java.simpleName, "marija exception ${e.localizedMessage}")
                _movies.value = LoadStateMovies.Error(e.localizedMessage)
            }
        }
    }

    suspend fun getMovies(): Flow<PagingData<Movie>> =
        withContext((Dispatchers.IO)) {
            return@withContext moviesRemoteDataSource.getPopularMovies()
        }
}
