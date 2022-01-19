package com.example.moviesapp.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.MoviesApplication
import com.example.moviesapp.common.dependancyinjection.activity.ActivityModule
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.common.dependancyinjection.presentation.UseCasesModule

open class BaseActivity : AppCompatActivity() {

    val activityComponent
        get() = (application as MoviesApplication).appComponent.newActivityComponent(
            ActivityModule(this))


    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule(), UseCasesModule())
    }

    protected val injector get() = presentationComponent
}