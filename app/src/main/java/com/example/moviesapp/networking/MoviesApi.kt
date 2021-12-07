package com.example.moviesapp.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    suspend fun queryPopularMovies(@Query("api_key") key: String) : Response<MovieListResponseSchema>
}