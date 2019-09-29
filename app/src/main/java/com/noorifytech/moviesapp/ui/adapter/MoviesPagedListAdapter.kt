package com.noorifytech.moviesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity
import kotlinx.android.synthetic.main.activity_movies_list_item.view.*

class MoviesPagedListAdapter(private val context: Context) :
    PagedListAdapter<MovieEntity, MoviesPagedListAdapter.MoviesViewHolder>(MOVIE_COMPARATOR) {

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(context, movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val viewItem = LayoutInflater.from(context)
            .inflate(R.layout.activity_movies_list_item, parent, false)
        return MoviesViewHolder(viewItem)
    }

    class MoviesViewHolder(private val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(context: Context, movie: MovieEntity) {
            viewItem.movieNameTV.text = movie.title

            Glide.with(context)
                .asBitmap()
                .load(movie.posterPath)
                .placeholder(R.mipmap.ic_launcher)
                .into(viewItem.movieImageIV)
        }

    }

    companion object {
        val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem == newItem
        }
    }
}