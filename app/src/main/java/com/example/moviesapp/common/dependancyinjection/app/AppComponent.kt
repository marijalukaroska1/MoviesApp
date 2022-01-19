package com.example.moviesapp.common.dependancyinjection.app

import com.example.moviesapp.common.dependancyinjection.activity.ActivityComponent
import com.example.moviesapp.common.dependancyinjection.activity.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule):ActivityComponent
}