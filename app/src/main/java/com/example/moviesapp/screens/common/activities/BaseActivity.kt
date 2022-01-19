package com.example.moviesapp.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.MoviesApplication
import com.example.moviesapp.common.dependancyinjection.activity.ActivityModule
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as MoviesApplication).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponentBuilder()
            .activity(this)
            .activityModule(ActivityModule())
            .build()
    }


    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector get() = presentationComponent
}