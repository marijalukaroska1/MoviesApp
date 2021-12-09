package com.example.moviesapp.movies
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: Int,
    @SerializedName("overview") val description : String,
    @SerializedName("poster_path") val posterPath: String
)
