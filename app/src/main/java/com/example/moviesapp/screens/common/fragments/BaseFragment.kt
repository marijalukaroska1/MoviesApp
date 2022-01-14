package com.example.moviesapp.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.moviesapp.common.dependancyinjection.presentation.DaggerPresentationComponent
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule((requireActivity() as BaseActivity).activityComponent))
            .build()
    }

    protected val injector get() = presentationComponent
}