package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import com.example.moviesapp.R
import com.example.moviesapp.screens.common.activities.BaseActivity

class MoviesListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, MoviesListFragment())
                .commit()
        }
    }
}