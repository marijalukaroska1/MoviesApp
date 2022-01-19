package com.example.moviesapp.screens.dialogs

import androidx.fragment.app.FragmentManager
import javax.inject.Inject


class DialogsNavigator @Inject constructor(private val fragmentManager: FragmentManager) {

    fun showServerErrorDialog() {
        fragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.newInstance(), null)
            .commitAllowingStateLoss()
    }
}