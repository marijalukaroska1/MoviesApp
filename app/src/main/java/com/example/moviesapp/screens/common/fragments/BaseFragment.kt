package com.example.moviesapp.screens.common.fragments

import androidx.fragment.app.Fragment
import com.example.moviesapp.screens.common.activities.BaseActivity

open class BaseFragment: Fragment() {

    protected val compositionRoot get() = (requireActivity() as BaseActivity).compositionRoot
}