package com.example.moviesapp.common.dependancyinjection

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.screens.ScreensNavigator

/**
 * ActivityCompositionRoot class defines which services
 * are scope to the activity and which services depend on activity in general
 *
 * This class just instantiates other classes and declare dependencies, and do not handle any functional concerns
 *
 * Composition Root should not expose Data Structures, only Objects
 */
class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screenNavigator by lazy {
        ScreensNavigator(activity)
    }

    //never use activity.applicationContext!!! it violets the Liskov substitution principle
    val application get() = appCompositionRoot.application

    val fragmentManager get() = activity.supportFragmentManager

    val layoutInflater get() = activity.layoutInflater

    val moviesApi get() = appCompositionRoot.moviesApi
}
