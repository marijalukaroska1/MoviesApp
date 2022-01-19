package com.example.moviesapp.common.dependancyinjection.app

import android.app.Application
import androidx.annotation.UiThread
import com.example.moviesapp.Constants
import com.example.moviesapp.networking.MoviesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Abstraction that encapsulates all the details
 * of construction and lifecycle management of the different services of the application
 *
 * This class just instantiates other classes and declare dependencies, and do not handle any functional concerns
 *
 *
 * With this class we are protected by design from accidentally
 * passing activity context into services which have longer life time, therefor by design we are protected
 * from memory leaks associated with capturing activity context in global scope
 */

// This annotation is a hint to future maintainers that everything
// inside this class should be accessed on UI thread exclusively
// lint will report if this condition is violated
@UiThread
@Module
class AppModule(val application: Application) {

    @Provides
    @AppScope
    fun retrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun moviesApi(retrofit: Retrofit) = retrofit.create(MoviesApi::class.java)

    @Provides
    fun application() = application
}