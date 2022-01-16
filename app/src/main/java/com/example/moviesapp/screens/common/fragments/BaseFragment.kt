package com.example.moviesapp.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector get() = presentationComponent
}