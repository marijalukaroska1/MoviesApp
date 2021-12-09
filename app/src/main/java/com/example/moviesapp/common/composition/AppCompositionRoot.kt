package com.example.moviesapp.common.composition

import com.example.moviesapp.Constants
import com.example.moviesapp.networking.MoviesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val moviesApi: MoviesApi = retrofit.create(MoviesApi::class.java)
}