package com.example.moviesapp.screens.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.example.moviesapp.Constants
import com.example.moviesapp.R
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.screens.common.views.BaseViewMvc
import com.example.moviesapp.screens.dialogs.DialogsNavigator

//parent: ViewGroup will be null in all activities, but in fragments it wont be null
class MoviesListViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?,
    dialogsNavigator: DialogsNavigator
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
    val moviesAdapter: MoviesAdapter

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

        moviesAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Error) {
                dialogsNavigator.showServerErrorDialog()
                hideProgressIndication()
            } else if (it.refresh is LoadState.NotLoading) {
                hideProgressIndication()
            } else if (it.refresh is LoadState.Loading) {
                showProgressIndication()
            }
        }

        recyclerView.adapter = moviesAdapter
    }

    suspend fun bindMovies(movies: PagingData<Movie>) {
        moviesAdapter.submitData(movies)
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
        PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MovieDiffCallBack()) {


        inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var title: TextView = view.findViewById(R.id.movie_title)
            var description: TextView = view.findViewById(R.id.movie_description)
            var image: ImageView = view.findViewById(R.id.movie_image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_movie_list_item, parent, false)
            return MoviesViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
            holder.title.text = getItem(position)?.title
            holder.description.text = getItem(position)?.description
            holder.image.load(Constants.IMAGE_BASE_URL + getItem(position)?.posterPath)
            holder.itemView.setOnClickListener {
                onMovieClickListener.invoke(getItem(position)!!)
            }
        }

        class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}