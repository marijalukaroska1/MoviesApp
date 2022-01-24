package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import android.util.Log
import com.example.moviesapp.R
import com.example.moviesapp.screens.ScreensNavigator
import com.example.moviesapp.screens.common.activities.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//This is a functional class
@AndroidEntryPoint
class MoviesListActivity : BaseActivity() {

    @Inject lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(this::class.java.simpleName, "$screensNavigator" + " " + this)
        setContentView(R.layout.activity_main)
    }
}