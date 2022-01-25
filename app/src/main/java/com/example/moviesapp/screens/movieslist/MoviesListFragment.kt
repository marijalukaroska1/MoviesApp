package com.example.moviesapp.screens.movieslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.LayoutMoviesListBinding
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.screens.ScreensNavigator
import com.example.moviesapp.screens.common.fragments.BaseFragment
import com.example.moviesapp.screens.common.imageloader.ImageLoader
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import com.example.moviesapp.screens.viewmodel.LoadStateMovies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesListFragment : BaseFragment(), OnMovieClickedListener {

    @Inject lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    @Inject lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

    @Inject lateinit var dialogsNavigator: DialogsNavigator

    @Inject lateinit var screensNavigator: ScreensNavigator

    @Inject lateinit var imageLoader: ImageLoader

    private lateinit var movieClickedListener: OnMovieClickedListener

    private var _binding: LayoutMoviesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesListAdapter: MoviesListAdapter

    private val moviesListViewModel: MoviesListViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        movieClickedListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutMoviesListBinding.inflate(inflater, container, false)

        moviesListAdapter = MoviesListAdapter({ clickedMovie ->
            movieClickedListener.onMovieClicked(clickedMovie)

        }, imageLoader)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = moviesListAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchMovies()

        binding.swipeRefresh.setOnRefreshListener {
            Log.d(this::class.java.simpleName, "marija setOnRefreshListener fetchMovies")
            fetchMovies()
        }

        moviesListAdapter.addLoadStateListener {
            Log.d(this::class.java.simpleName, "marija n add state listener")
            if (it.refresh is LoadState.Error) {
                Log.d(this::class.java.simpleName, "marija n add state listener refresh")
                dialogsNavigator.showServerErrorDialog()
                hideProgressIndication()
            } else if (it.refresh is LoadState.NotLoading) {
                Log.d(this::class.java.simpleName, "marija n add state listener NotLoading")
                hideProgressIndication()
            } else if (it.refresh is LoadState.Loading) {
                Log.d(this::class.java.simpleName, "marija n add state listener Loading")
                showProgressIndication()
            }
        }
    }

    private fun fetchMovies() {
        showProgressIndication()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesListViewModel.movies.collect {
                    when (it) {
                        is LoadStateMovies.Success -> {
                            hideProgressIndication()
                            it.movies.collect { movies ->
                                Log.d(this::class.java.simpleName, "marija success movies $movies")
                                moviesListAdapter.submitData(movies)
                            }
                        }
                        is LoadStateMovies.Error -> {
                            Log.e(this::class.java.simpleName, "Error fetching movies ${it.errorMessage}")
                            hideProgressIndication()
                            dialogsNavigator.showServerErrorDialog()
                        }
                    }
                }
            }
        }
    }

    override fun onMovieClicked(clickedMovie: Movie) {
        screensNavigator.toMovieDetails(requireView(), clickedMovie.id)
    }

    fun showProgressIndication() {
        binding.swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (binding.swipeRefresh.isRefreshing) {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface OnMovieClickedListener {
    fun onMovieClicked(clickedMovie: Movie)
}
