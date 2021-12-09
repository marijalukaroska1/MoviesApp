package com.example.moviesapp.movies

import com.google.gson.annotations.SerializedName

data class MovieWithBody(
    @SerializedName("original_title") val title: String,
    @SerializedName("id") val id: Int,
    @SerializedName("overview") val description: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String
) {
    override fun toString(): String {
        return "MovieWithBody(title='$title', id=$id, description='$description', releaseDate='$releaseDate')"
    }
}
