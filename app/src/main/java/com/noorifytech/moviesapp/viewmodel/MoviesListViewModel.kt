package com.noorifytech.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.noorifytech.moviesapp.repository.MoviesRepository
import com.noorifytech.moviesapp.vo.MoviesVO
import com.noorifytech.moviesapp.vo.Resource

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getMoviesList(page: Int = 1): LiveData<Resource<List<MoviesVO>>> {
        return moviesRepository.getMoviesList()
    }
}