package com.example.moviesapp.common.dependancyinjection.app

import com.example.moviesapp.common.dependancyinjection.activity.ActivityComponent
import com.example.moviesapp.common.dependancyinjection.activity.ActivityModule
import com.example.moviesapp.common.dependancyinjection.service.ServiceModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newServiceComponent(serviceModule: ServiceModule)
    fun newActivityComponent(activityModule: ActivityModule):ActivityComponent
}