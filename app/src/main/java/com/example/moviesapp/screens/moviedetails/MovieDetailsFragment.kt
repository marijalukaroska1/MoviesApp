package com.example.moviesapp.screens.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.screens.common.fragments.BaseFragment
import com.example.moviesapp.screens.dialogs.DialogsNavigator

class MovieDetailsFragment : BaseFragment() {

    private lateinit var viewMvc: MovieDetailsViewMvc
    private lateinit var dialogsNavigator: DialogsNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = MovieDetailsViewMvc(inflater, container, dialogsNavigator)
        return viewMvc.rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogsNavigator = compositionRoot.dialogsNavigator
    }
}