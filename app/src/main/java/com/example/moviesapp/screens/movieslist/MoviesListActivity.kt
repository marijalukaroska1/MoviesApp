package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import com.example.moviesapp.R
import com.example.moviesapp.screens.common.activities.BaseActivity

//This is a functional class
class MoviesListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}