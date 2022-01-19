package com.example.moviesapp.movies

import android.util.Log
import com.example.moviesapp.Constants
import com.example.moviesapp.networking.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class FetchMovieDetailsUseCase @Inject constructor(private val moviesApi: MoviesApi) {

    sealed class Result {
        data class Success(val movieWithBody: MovieWithBody) : Result()
        object Failure : Result()
    }

    suspend fun fetchMovieDetails(movieId: Int): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = moviesApi.queryMovieDetails(movieId, Constants.MOVIES_DB_API_KEY)
                Log.d(this::class.java.simpleName, "response: " + response.isSuccessful)
                if (response.isSuccessful && response.body() != null) {
                    Log.d(this::class.java.simpleName, "response: " + response.body())
                    return@withContext Result.Success(response.body()!!)
                } else {
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