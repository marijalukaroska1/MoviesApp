package com.example.moviesapp.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseFragment: Fragment() {

    protected val appCompositionRoot get() = (requireActivity() as BaseActivity).appCompositionRoot
}