package com.noorifytech.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.vo.MoviesVO
import kotlinx.android.synthetic.main.activity_movies_list_item.view.*

class MoviesListRecyclerViewAdapter(private val context: Context, var moviesList: List<MoviesVO>) :
    RecyclerView.Adapter<MoviesListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.activity_movies_list_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(context, movie.name, movie.imageUrl)
    }

    override fun getItemCount(): Int = moviesList.size

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

}