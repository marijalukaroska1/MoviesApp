package com.example.moviesapp.screens.viewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.moviesapp.R
import com.example.moviesapp.screens.common.activities.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewModelActivity : BaseActivity() {


    private val myViewModel: MyViewModel by viewModels()
    private val mySecondViewModel: MySecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_view_model)

        myViewModel.movies.observe(this, Observer { moviesList ->
            Toast.makeText(this, "movies data was fetched successfully", Toast.LENGTH_SHORT).show()
        })
    }
}