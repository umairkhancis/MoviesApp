package com.noorifytech.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.vo.MovieVO

interface MoviesRepository {
    fun getPopularMovies(): LiveData<PagedList<MovieVO>>
}