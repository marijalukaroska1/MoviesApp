package com.example.moviesapp.screens.viewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.R
import com.example.moviesapp.screens.common.activities.BaseActivity
import javax.inject.Inject

class ViewModelActivity : BaseActivity() {

    @Inject
    lateinit var myViewModelFactory: MyViewModel.Factory
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_view_model)

        myViewModel = ViewModelProvider(this, myViewModelFactory).get(MyViewModel::class.java)

        myViewModel.movies.observe(this, Observer { moviesList ->
            Toast.makeText(this, "movies data was fetched successfully", Toast.LENGTH_SHORT).show()
        })
    }
}