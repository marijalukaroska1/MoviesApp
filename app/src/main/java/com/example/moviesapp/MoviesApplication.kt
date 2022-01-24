package com.example.moviesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//when you use hilt you custom application class needs to be annotated with this HiltAndroidApp annotation
@HiltAndroidApp
class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}