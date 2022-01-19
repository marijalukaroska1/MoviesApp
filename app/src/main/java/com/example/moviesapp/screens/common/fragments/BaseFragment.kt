package com.example.moviesapp.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.common.dependancyinjection.presentation.UseCasesModule
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {

    //by lazy means that only one instance will be created of the presentation component and
    //we will have the same reference each time we try to access the presentation component
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule(), UseCasesModule())
    }

    protected val injector get() = presentationComponent
}