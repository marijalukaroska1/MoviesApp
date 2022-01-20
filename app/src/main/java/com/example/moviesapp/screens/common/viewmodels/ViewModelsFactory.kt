package com.example.moviesapp.screens.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.screens.viewmodel.MyViewModel
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

//using providers will insure that whenever new instance is created of MyViewModel, it will use MoviesRemoteDataSource according to the Dagger policy
class ViewModelsFactory @Inject constructor(private val myViewModelProvider: Provider<MyViewModel>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MyViewModel::class.java -> myViewModelProvider.get() as T
            else -> throw RuntimeException("unsupported viewmodel type: $modelClass")
        }
    }
}