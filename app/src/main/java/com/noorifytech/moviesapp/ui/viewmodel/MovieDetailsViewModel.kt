package com.noorifytech.moviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.noorifytech.moviesapp.data.repository.MoviesRepository

class MovieDetailsViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getMovieDetails(movieId: Int) = moviesRepository.getMovieDetails(movieId)
}