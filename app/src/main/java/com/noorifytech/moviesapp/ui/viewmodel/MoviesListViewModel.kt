package com.noorifytech.moviesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.data.repository.MoviesRepository

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var result: LiveData<PagedList<MovieEntity>>

    init {
        result = moviesRepository.getPopularMovies()
    }

    fun getPopularMoviesLiveData() = result
}