package com.example.moviesapp.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.MoviesApplication
import com.example.moviesapp.common.dependancyinjection.ActivityCompositionRoot
import com.example.moviesapp.common.dependancyinjection.Injector
import com.example.moviesapp.common.dependancyinjection.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MoviesApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}