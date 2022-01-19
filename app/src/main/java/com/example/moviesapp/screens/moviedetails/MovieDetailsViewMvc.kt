package com.example.moviesapp.screens.moviedetails

import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviesapp.Constants
import com.example.moviesapp.R
import com.example.moviesapp.movies.MovieWithBody
import com.example.moviesapp.screens.common.imageloader.ImageLoader
import com.example.moviesapp.screens.common.views.BaseViewMvc

class MovieDetailsViewMvc(
    layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    parent: ViewGroup?
) : BaseViewMvc<MovieDetailsViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.layout_movie_details
) {

    interface Listener {
        fun onRefreshClicked()
    }

    private val moviePosterCard : CardView
    private val moviePoster: ImageView
    private val swipeRefresh: SwipeRefreshLayout
    private val movieTitle: TextView
    private val movieDescription: TextView
    private val movieReleaseDate: TextView

    init {
        moviePosterCard = findViewById(R.id.movie_card)
        moviePoster = findViewById(R.id.movie_image)
        movieTitle = findViewById(R.id.movie_title)
        movieDescription = findViewById(R.id.movie_description)
        movieReleaseDate = findViewById(R.id.movie_release_date)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }
    }

    fun bindMovie(movieWithBody: MovieWithBody) {
        moviePosterCard.visibility = VISIBLE
        movieReleaseDate.visibility = VISIBLE
        moviePoster.visibility = VISIBLE
        movieTitle.text = movieWithBody.title
        movieDescription.text = movieWithBody.description
        val releaseDate = context.getString(R.string.release_date, movieWithBody.releaseDate)
        movieReleaseDate.text = releaseDate
        
        imageLoader.loadImage(Constants.IMAGE_BASE_URL + movieWithBody.posterPath, moviePoster)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }
}