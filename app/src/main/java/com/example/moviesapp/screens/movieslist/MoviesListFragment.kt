package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.screens.ScreensNavigator
import com.example.moviesapp.screens.common.fragments.BaseFragment
import com.example.moviesapp.screens.common.views.ViewMvcFactory
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


class MoviesListFragment : BaseFragment(), MoviesListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject
    lateinit var fetchMoviesUseCase: FetchMoviesUseCase
    @Inject
    lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    @Inject
    lateinit var dialogsNavigator: DialogsNavigator
    @Inject
    lateinit var screensNavigator: ScreensNavigator
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var viewMvc: MoviesListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        Log.e(this::class.java.simpleName, "$screensNavigator  ${requireActivity()}")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(this::class.java.simpleName, "onCreateView()")
        viewMvc = viewMvcFactory.getMoviesListViewMvc(container, dialogsNavigator)
        return viewMvc.rootView
    }

    override fun onRefreshClicked() {
        fetchMovies()
    }


    override fun onMovieClicked(clickedMovie: Movie) {
        screensNavigator.toMovieDetails(viewMvc.rootView, clickedMovie.id)
    }

    override fun onViewModelClicked() {
        screensNavigator.toViewModelScreen(viewMvc.rootView.context)
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
