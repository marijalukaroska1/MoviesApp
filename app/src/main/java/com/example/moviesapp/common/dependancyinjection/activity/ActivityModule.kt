package com.example.moviesapp.common.dependancyinjection.activity

import androidx.appcompat.app.AppCompatActivity
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
class ActivityModule() {

    //to make this code more performant we need to make provider methods static (in kotlin its done with companion object)
    //the code that dagger will generate behind the scences will be more performant
    companion object {
        @Provides
        @ActivityScope
        //when a service is scoped it means that there is some kind of state inside that object
        //that needs to be shared among multiple clients
        fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

        @Provides
        fun layoutInflater(activity: AppCompatActivity) = activity.layoutInflater
    }
}
