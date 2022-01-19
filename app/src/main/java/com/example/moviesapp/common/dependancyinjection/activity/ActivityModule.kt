package com.example.moviesapp.common.dependancyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.common.dependancyinjection.app.AppComponent
import com.example.moviesapp.screens.ScreensNavigator
import dagger.Module
import dagger.Provides

/**
 * ActivityModule(which is the same as ActivityCompositionRoot) class defines which services
 * are scope to the activity and which services depend on activity in general
 *
 * This class just instantiates other classes and declare dependencies, and do not handle
 * any functional concerns
 */
@Module
class ActivityModule(
    //bootstrapping dependencies
    private val activity: AppCompatActivity) {

    @Provides
    fun activity() = activity

    @Provides
    fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

    @Provides
    fun layoutInflater(activity: AppCompatActivity) = activity.layoutInflater
}
