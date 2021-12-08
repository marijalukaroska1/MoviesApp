package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.screens.common.fragments.BaseFragment
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

class MoviesListFragment : BaseFragment(), MoviesListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: MoviesListViewMvc

    private lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    private lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

    private lateinit var dialogsNavigator: DialogsNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchMoviesUseCase = FetchMoviesUseCase(appCompositionRoot.moviesApi)
        moviesRemoteDataSource = FetchMoviesUseCase.MoviesRemoteDataSourceImpl(appCompositionRoot.moviesApi)
        dialogsNavigator = DialogsNavigator(requireActivity().supportFragmentManager)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMvc = MoviesListViewMvc(LayoutInflater.from(context), null, dialogsNavigator)
        return viewMvc.rootView
    }

    override fun onRefreshClicked() {
        fetchMovies()
    }


    override fun onMovieClicked(clickedMovie: Movie) {
        TODO("Not yet implemented")
    }

    private fun fetchMovies() {
        coroutineScope.launch {
            moviesRemoteDataSource.getPopularMovies().collectLatest { movies ->
                Log.d(this::class.java.simpleName, "movies: " + movies.toString())
                viewMvc.bindMovies(movies)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchMovies()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

}
