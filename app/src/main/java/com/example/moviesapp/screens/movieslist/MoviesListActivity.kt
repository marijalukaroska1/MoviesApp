package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.Constants
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.networking.MoviesApi
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesListActivity : AppCompatActivity(), MoviesListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: MoviesListViewMvc

    private lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    private lateinit var dialogsNavigator: DialogsNavigator

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val moviesApi : MoviesApi = retrofit.create(MoviesApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = MoviesListViewMvc(LayoutInflater.from(this), null)
        Log.d(this::class.java.simpleName, "retrofit: " + retrofit.baseUrl())
        fetchMoviesUseCase = FetchMoviesUseCase(moviesApi)
        dialogsNavigator = DialogsNavigator(this.supportFragmentManager)
        setContentView(viewMvc.rootView)
    }

    override fun onRefreshClicked() {
        fetchMovies()
    }

    private fun fetchMovies() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                val result = fetchMoviesUseCase.fetchPopularMovies()
                when (result) {
                    is FetchMoviesUseCase.Result.Success -> {
                        viewMvc.bindMovies(result.popularMovies)
                        //isDataLoaded = true
                    }
                    is FetchMoviesUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    override fun onMovieClicked(clickedMovie: Movie) {
       //TODO
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