package com.noorifytech.moviesapp.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.noorifytech.moviesapp.repository.MoviesRepository

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val result = moviesRepository.getPopularMovies()

    val popularMovies = Transformations.map(result) { it }
}