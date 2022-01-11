package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.common.composition.AppCompositionRoot

class MoviesApplication : Application() {

    // lateinit = initialized after the declaration.
    // we use this when we are sure that the variable will be initialized before using it
    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        super.onCreate()
    }
}