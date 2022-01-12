package com.example.moviesapp.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.moviesapp.common.dependancyinjection.Injector
import com.example.moviesapp.common.dependancyinjection.PresentationCompositionRoot
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {

    private val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}