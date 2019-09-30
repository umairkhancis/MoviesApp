package com.noorifytech.moviesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.vo.MovieVO

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var popularMoviesLiveData: LiveData<PagedList<MovieVO>> = moviesRepository.getPopularMovies()

    fun getPopularMoviesLiveData() = popularMoviesLiveData
}