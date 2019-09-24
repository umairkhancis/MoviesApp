package com.noorifytech.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.vo.MovieVO
import kotlinx.android.synthetic.main.activity_movies_list_item.view.*

class MoviesPagedListAdapter(private val context: Context) :
    PagedListAdapter<MovieVO, MoviesPagedListAdapter.ViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_movies_list_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = this.currentList?.get(position)
        if (movie != null) {
            holder.bind(context, movie.name, movie.imageUrl)
        }
    }

    override fun getItemCount(): Int = this.currentList?.size ?: 0


    class ViewHolder(private val viewItem: View) : RecyclerView.ViewHolder(viewItem) {

        fun bind(context: Context, movieName: String, imageUrl: String) {
            viewItem.movieNameTV.text = movieName

            Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(viewItem.movieImageIV)
        }

    }

    companion object {
        val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieVO>() {
            override fun areItemsTheSame(oldItem: MovieVO, newItem: MovieVO) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieVO, newItem: MovieVO) =
                oldItem == newItem
        }
    }
}