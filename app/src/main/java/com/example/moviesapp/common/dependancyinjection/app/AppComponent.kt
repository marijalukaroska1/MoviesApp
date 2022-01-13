package com.example.moviesapp.common.dependancyinjection.app

import android.app.Application
import com.example.moviesapp.networking.MoviesApi
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun moviesApi(): MoviesApi

    fun retrofit(): Retrofit

    fun application(): Application
}