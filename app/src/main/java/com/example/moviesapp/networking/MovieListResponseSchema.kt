package com.example.moviesapp.networking

import com.google.gson.annotations.SerializedName

data class MovieListResponseSchema<T>(
    @SerializedName("results") val popularMovies: List<T>
)
