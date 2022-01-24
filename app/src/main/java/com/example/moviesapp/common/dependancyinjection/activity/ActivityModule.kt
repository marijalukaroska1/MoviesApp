package com.example.moviesapp.common.dependancyinjection.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.networking.MoviesApi
import com.example.moviesapp.screens.ScreensNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * ActivityModule(which is the same as ActivityCompositionRoot) class defines which services
 * are scope to the activity and which services depend on activity in general
 *
 * This class just instantiates other classes and declare dependencies, and do not handle
 * any functional concerns
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun moviesRemoteDataSource(moviesRemoteDataSourceImpl: FetchMoviesUseCase.MoviesRemoteDataSourceImpl) : MoviesRemoteDataSource

    //to make this code more performant we need to make provider methods static (in kotlin its done with companion object)
    //the code that dagger will generate behind the scences will be more performant
    companion object {
        @Provides
        fun appCompatActivity(activity: Activity): AppCompatActivity = activity as AppCompatActivity

        @Provides
        @ActivityScoped
        //when a service is scoped it means that there is some kind of state inside that object
        //that needs to be shared among multiple clients
        fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

        @Provides
        fun layoutInflater(activity: AppCompatActivity) = activity.layoutInflater
    }
}
