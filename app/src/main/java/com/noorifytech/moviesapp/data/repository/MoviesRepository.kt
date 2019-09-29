package com.noorifytech.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity

interface MoviesRepository {
    fun getPopularMovies(): LiveData<PagedList<MovieEntity>>
}