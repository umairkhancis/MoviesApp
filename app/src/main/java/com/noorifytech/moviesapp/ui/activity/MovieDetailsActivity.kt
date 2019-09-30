package com.noorifytech.moviesapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.ui.viewmodel.MovieDetailsViewModel
import com.noorifytech.moviesapp.ui.viewmodel.factory.MovieDetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: MovieDetailsViewModelFactory
    private lateinit var viewModel: MovieDetailsViewModel

    private val movieId: Int by lazy { intent.getIntExtra(MOVIE_ID_KEY, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_details)

        viewModelFactory = MovieDetailsViewModelFactory.create()
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MovieDetailsViewModel::class.java)

        init()
    }

    private fun init() {
        viewModel.getMovieDetails(movieId)
            .observe(this, Observer<MovieDetailVO?> { movieDetails ->
                if (movieDetails != null) {
                    movieNameTV.text = movieDetails.title
                    movieOverviewTV.text = movieDetails.overview
                    movieReleaseDateTV.text = movieDetails.getReleaseDate()

                    Glide.with(this)
                        .asBitmap()
                        .load(movieDetails.imageUrl)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(movieImageIV)
                }
            })
    }

    companion object {
        const val MOVIE_ID_KEY = "movie_id"
    }
}