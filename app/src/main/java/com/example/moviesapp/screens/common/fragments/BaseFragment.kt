package com.example.moviesapp.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.moviesapp.common.dependancyinjection.DaggerPresentationComponent
import com.example.moviesapp.common.dependancyinjection.Injector
import com.example.moviesapp.common.dependancyinjection.PresentationModule
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule((requireActivity() as BaseActivity).activityCompositionRoot))
            .build()
    }

    protected val injector get() = Injector(presentationComponent)
}