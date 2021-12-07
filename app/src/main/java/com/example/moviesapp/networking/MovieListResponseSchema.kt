package com.example.moviesapp.networking

import com.example.moviesapp.movies.Movie
import com.google.gson.annotations.SerializedName

class MovieListResponseSchema(@SerializedName("results") val popularMovies: List<Movie>) {
}