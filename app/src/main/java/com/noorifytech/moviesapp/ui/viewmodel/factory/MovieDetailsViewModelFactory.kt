package com.noorifytech.moviesapp.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.factory.MoviesAppFactory
import com.noorifytech.moviesapp.ui.viewmodel.MovieDetailsViewModel

class MovieDetailsViewModelFactory(
    private val moviesRepository: MoviesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


    companion object {
        fun create(): MovieDetailsViewModelFactory {
            val moviesRepo = MoviesAppFactory.getMoviesRepository()
            return MovieDetailsViewModelFactory(moviesRepo)
        }
    }
}