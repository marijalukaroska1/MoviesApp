package com.example.moviesapp.screens.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.common.dependancyinjection.Service
import com.example.moviesapp.movies.FetchMovieDetailsUseCase
import com.example.moviesapp.screens.common.fragments.BaseFragment
import com.example.moviesapp.screens.common.views.ViewMvcFactory
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import kotlinx.coroutines.*
import retrofit2.http.Field

class MovieDetailsFragment : BaseFragment(), MovieDetailsViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @field:Service private lateinit var dialogsNavigator: DialogsNavigator
    @field:Service private lateinit var fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
    @field:Service private lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var viewMvc: MovieDetailsViewMvc

    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
        Log.d(this::class.java.simpleName, "context: " + context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewMvc = viewMvcFactory.getMovieDetailsViewMvc(container)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        movieId = arguments?.getInt("movieId")!!
        Log.d(this::class.java.simpleName, "movieId: " + movieId)
        fetchMovieDetails()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchMovieDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchMovieDetailsUseCase.fetchMovieDetails(movieId)
                when (result) {
                    is FetchMovieDetailsUseCase.Result.Success -> {
                        Log.d(this::class.java.simpleName, "movieWithBody: " + result.movieWithBody)
                        viewMvc.bindMovie(result.movieWithBody)
                    }

                    is FetchMovieDetailsUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    override fun onRefreshClicked() {
        fetchMovieDetails()
    }
}