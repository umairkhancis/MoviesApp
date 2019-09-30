package com.noorifytech.moviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.data.repository.vo.Resource

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val popularMoviesResource: Resource<PagedList<MovieVO>> = moviesRepository.getPopularMovies()

    fun getPopularMovies() = popularMoviesResource.data

    fun getPopularMoviesNetworkStatus() = popularMoviesResource.networkStatus
}