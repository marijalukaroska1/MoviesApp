package com.example.moviesapp.movies

import android.util.Log
import com.example.moviesapp.Constants
import com.example.moviesapp.networking.MoviesApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchMoviesUseCase(private val moviesApi: MoviesApi) {

    sealed class Result {
        class Success(val popularMovies: List<Movie>) : Result()
        object Failure : Result()
    }

    suspend fun fetchPopularMovies(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = moviesApi.queryPopularMovies(Constants.MOVIES_DB_API_KEY)
                if (response.isSuccessful && response.body() != null) {
                    Log.d(this::class.java.simpleName, "success")
                    return@withContext Result.Success(response.body()!!.popularMovies)
                } else {
                    Log.d(this::class.java.simpleName, "error: " + response)
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }
}