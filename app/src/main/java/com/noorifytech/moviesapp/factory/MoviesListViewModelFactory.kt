package com.noorifytech.moviesapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.noorifytech.moviesapp.repository.MoviesRepository
import com.noorifytech.moviesapp.viewmodel.MoviesListViewModel
import com.noorifytech.moviesapp.vo.MovieVO


class MoviesListViewModelFactory(
    private val moviesRepository: MoviesRepository,
    private val popularMoviesCB: PagedList.BoundaryCallback<MovieVO>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            return MoviesListViewModel(moviesRepository, popularMoviesCB) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}