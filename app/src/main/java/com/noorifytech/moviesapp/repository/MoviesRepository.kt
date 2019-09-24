package com.noorifytech.moviesapp.repository

import androidx.paging.DataSource
import com.noorifytech.moviesapp.vo.MovieVO

interface MoviesRepository {
    fun getPopularMovies(): DataSource.Factory<Int, MovieVO>
}