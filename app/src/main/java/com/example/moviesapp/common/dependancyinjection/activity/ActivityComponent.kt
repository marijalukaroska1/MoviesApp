package com.example.moviesapp.common.dependancyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.moviesapp.common.dependancyinjection.app.AppComponent
import com.example.moviesapp.networking.MoviesApi
import com.example.moviesapp.screens.ScreensNavigator
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): AppCompatActivity

    fun screenNavigator(): ScreensNavigator

    fun fragmentManager(): FragmentManager

    fun layoutInflater(): LayoutInflater

    //AppComponent exposes moviesApi, and ActivityComponent
    fun moviesApi(): MoviesApi
}