package com.example.moviesapp.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.MoviesApplication

open class BaseActivity : AppCompatActivity() {

    val appCompositionRoot get() = (application as MoviesApplication).appCompositionRoot

}