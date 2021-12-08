package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.Constants
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.networking.MoviesApi
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesListActivity : AppCompatActivity(), MoviesListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var viewMvc: MoviesListViewMvc

    private lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    private lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

    private lateinit var dialogsNavigator: DialogsNavigator

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val moviesApi: MoviesApi = retrofit.create(MoviesApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogsNavigator = DialogsNavigator(this.supportFragmentManager)
        viewMvc = MoviesListViewMvc(LayoutInflater.from(this), null, dialogsNavigator)
        fetchMoviesUseCase = FetchMoviesUseCase(moviesApi)
        moviesRemoteDataSource = FetchMoviesUseCase.MoviesRemoteDataSourceImpl(moviesApi)
        setContentView(viewMvc.rootView)
    }

    override fun onRefreshClicked() {
        fetchMovies()
    }

    private fun fetchMovies() {
        coroutineScope.launch {
            moviesRemoteDataSource.getPopularMovies().collectLatest { movies ->
                Log.d(this::class.java.simpleName, "movies: " + movies.toString())
                viewMvc.bindMovies(movies)
            }
        }

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