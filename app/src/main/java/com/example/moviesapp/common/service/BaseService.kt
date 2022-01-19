package com.example.moviesapp.common.service

import android.app.Service
import com.example.moviesapp.MoviesApplication
import com.example.moviesapp.common.dependancyinjection.service.ServiceModule

abstract class BaseService : Service() {

    private val appComponent get() = (application as MoviesApplication).appComponent

    val serviceComponent by lazy {
        appComponent.newServiceComponent(ServiceModule(this))
    }
}