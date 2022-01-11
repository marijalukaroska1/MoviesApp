package com.example.moviesapp.common.composition

import androidx.annotation.UiThread
import com.example.moviesapp.Constants
import com.example.moviesapp.movies.FetchMovieDetailsUseCase
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.networking.MoviesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Abstraction that encapsulates all the details
 * of construction and lifecycle management of the different services of the application
 */

// This annotation is a hint to future maintainers that everything
// inside this class should be accessed on UI thread exclusively
// lint will report if this condition is violated
@UiThread
class AppCompositionRoot {

    //DI-segregation of application's logic into two sets of classes:

    // 1. Functional set:
    // contains classes that encapsulate core application's functionality
    // - whatever you application doesn all of this functionality belongs to functional set


    // 2. Construction set:
    // contains classes that resolve dependencies and instantiate objects from functional sets
    // classes in construction set do not provide any user visible functionality
    // but they know how to wire the functional classes to make the application work


    // functional and construction sets must be disjoint


    //Initialization by lazy { ... } is thread-safe by default and guarantees that the initializer is invoked at most once
    //Lazy properties: the value is computed only on first access
    // INITIALIZATION IS LAZY AND WILL ONE SINGLE INSTANCE SHARED AMONG ALL THE CLIENTS THAT USE IT

    //    private var _retrofit: Retrofit? = null
//
//    private val retrofit1: Retrofit =
//        if (_retrofit == null) {
//            _retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            _retrofit!!
//        } else {
//            _retrofit!!
//        }
//
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val moviesApi: MoviesApi by lazy {
        retrofit.create(MoviesApi::class.java)
    }
}