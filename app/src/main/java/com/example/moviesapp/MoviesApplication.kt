package com.example.moviesapp

import android.app.Application
import com.example.moviesapp.common.composition.AppCompositionRoot

class MoviesApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        super.onCreate()
    }
}