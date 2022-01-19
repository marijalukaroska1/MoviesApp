package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.common.dependancyinjection.app.AppComponent
import com.example.moviesapp.common.dependancyinjection.app.AppModule
import com.example.moviesapp.common.dependancyinjection.app.DaggerAppComponent

class MoviesApplication : Application() {

    // lateinit = initialized after the declaration.
    // we use this when we are sure that the variable will be initialized before using it
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}