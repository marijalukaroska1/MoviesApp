package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import android.util.Log
import com.example.moviesapp.R
import com.example.moviesapp.screens.ScreensNavigator
import com.example.moviesapp.screens.common.activities.BaseActivity
import com.example.moviesapp.screens.moviedetails.MovieDetailsFragment
import javax.inject.Inject

//This is a functional class
class MoviesListActivity : BaseActivity() {

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)

        Log.e(this::class.java.simpleName, "$screensNavigator" + " " + this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}