package com.example.moviesapp.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseDialog : DialogFragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule()
        )
    }

    protected val injector get() = presentationComponent
}