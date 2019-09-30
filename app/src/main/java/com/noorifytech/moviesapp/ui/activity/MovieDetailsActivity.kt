package com.noorifytech.moviesapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.data.repository.vo.NetworkStatus
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
        val movieDetailsResource = viewModel.getMovieDetails(movieId)

        movieDetailsResource.data?.observe(this, Observer<MovieDetailVO?> { movieDetails ->
            if (movieDetails != null) {
                movieNameTV.text = movieDetails.title
                movieOverviewTV.text = movieDetails.overview
                movieReleaseDateTV.text = movieDetails.getReleaseDate()

                Glide.with(this)
                    .asBitmap()
                    .load(movieDetails.imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(movieImageIV)
            } else {
                if (movieDetailsResource.networkStatus.value == NetworkStatus.LOADING) {
                    Toast.makeText(
                        this, NetworkStatus.LOADING.name,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        movieDetailsResource.networkStatus.observe(this, Observer { networkStatus ->
            when (networkStatus) {
                NetworkStatus.LOADING -> Toast.makeText(
                    this,
                    NetworkStatus.LOADING.name,
                    Toast.LENGTH_SHORT
                ).show()

                NetworkStatus.SUCCESS -> Toast.makeText(
                    this,
                    NetworkStatus.SUCCESS.name,
                    Toast.LENGTH_SHORT
                ).show()

                NetworkStatus.FAILED -> Toast.makeText(
                    this,
                    NetworkStatus.FAILED.name,
                    Toast.LENGTH_SHORT
                ).show()

                else -> Toast.makeText(this, NetworkStatus.FAILED.name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        const val MOVIE_ID_KEY = "movie_id"
    }
}