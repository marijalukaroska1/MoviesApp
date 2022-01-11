package com.example.moviesapp.common.composition

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.screens.ScreensNavigator

/**
 * ActivityCompositionRoot class defines which services
 * are scope to the activity and which services depend on activity in general
 */
class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screenNavigator by lazy {
        ScreensNavigator(activity)
    }

    val fragmentManager get() = activity.supportFragmentManager

    val layoutInflater get() = activity.layoutInflater

    val moviesApi get() = appCompositionRoot.moviesApi
}
