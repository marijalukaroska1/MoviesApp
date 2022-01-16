package com.example.moviesapp.common.dependancyinjection.app

import android.app.Application
import com.example.moviesapp.common.dependancyinjection.activity.ActivityComponent
import com.example.moviesapp.common.dependancyinjection.activity.ActivityModule
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationComponent
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.networking.MoviesApi
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule):ActivityComponent
}