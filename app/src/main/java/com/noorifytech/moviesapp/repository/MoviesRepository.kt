package com.noorifytech.moviesapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.noorifytech.moviesapp.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.vo.MovieVO

interface MoviesRepository {
    fun getPopularMovies(): LiveData<PagedList<MovieEntity>>
}