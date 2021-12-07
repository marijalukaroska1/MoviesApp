package com.example.moviesapp.screens.movieslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviesapp.R
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.screens.common.BaseViewMvc

class MoviesListViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<MoviesListViewMvc.Listener>(
    layoutInflater,
    parent,
    R.layout.layout_movies_list
) {

    interface Listener {
        fun onRefreshClicked()
        fun onMovieClicked(clickedMovie: Movie)
    }

    private val swipeRefresh: SwipeRefreshLayout
    private val recyclerView: RecyclerView
    private val moviesAdapter: MoviesAdapter

    init {

        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        moviesAdapter = MoviesAdapter { clickedMovie ->
            for (listener in listeners) {
                listener.onMovieClicked(clickedMovie)
            }
        }

        recyclerView.adapter = moviesAdapter
    }

    fun bindMovies(movies: List<Movie>) {
        Log.d(this::class.java.simpleName, "movies: " + movies)
        moviesAdapter.bindData(movies)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    class MoviesAdapter(private val onMovieClickListener: (Movie) -> Unit) :
        RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

        private var moviesList: List<Movie> = ArrayList(0)

        inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var title: TextView = view.findViewById(R.id.txt_title)
        }

        fun bindData(movies: List<Movie>) {
            moviesList = ArrayList(movies)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_movie_list_item, parent, false)
            return MoviesViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
            holder.title.text = moviesList[position].title
            holder.itemView.setOnClickListener {
                onMovieClickListener.invoke(moviesList[position])
            }
        }

        override fun getItemCount(): Int {
            return moviesList.size
        }
    }

}