package com.example.moviesapp.networking

import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MovieWithBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    suspend fun queryPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): MovieListResponseSchema<Movie>

    @GET("3/movie/{movie_id}")
    suspend fun queryMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String
    ): Response<MovieWithBody>
}