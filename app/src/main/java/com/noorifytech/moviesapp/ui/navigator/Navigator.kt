package com.noorifytech.moviesapp.ui.navigator

import android.app.Activity
import android.content.Intent
import com.noorifytech.moviesapp.ui.activity.MovieDetailsActivity

object Navigator {

    fun navigateToMovieDetailsScreen(activity: Activity, movieId: Int) {
        val movieDetailsIntent = Intent(activity, MovieDetailsActivity::class.java)
        movieDetailsIntent.putExtra(MovieDetailsActivity.MOVIE_ID_KEY, movieId)
        activity.startActivity(movieDetailsIntent)
    }
}