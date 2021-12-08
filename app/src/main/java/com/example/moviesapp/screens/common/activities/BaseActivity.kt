package com.example.moviesapp.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.MoviesApplication
import com.example.moviesapp.common.composition.ActivityCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MoviesApplication).appCompositionRoot

    val compositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

}