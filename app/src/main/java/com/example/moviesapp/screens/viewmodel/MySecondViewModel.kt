package com.example.moviesapp.screens.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviesapp.movies.MoviesRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MySecondViewModel @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) : ViewModel() {
}