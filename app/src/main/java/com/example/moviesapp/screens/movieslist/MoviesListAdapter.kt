package com.example.moviesapp.screens.movieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.Constants
import com.example.moviesapp.R
import com.example.moviesapp.movies.Movie
import com.example.moviesapp.screens.common.imageloader.ImageLoader


class MoviesListAdapter(
    private val onMovieClickListener: (Movie) -> Unit,
    private val imageLoader: ImageLoader
) :
    PagingDataAdapter<Movie, MoviesListAdapter.MoviesViewHolder>(MovieDiffCallBack()) {


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
        imageLoader.loadImage(
            Constants.IMAGE_BASE_URL + getItem(position)?.posterPath,
            holder.image
        )
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