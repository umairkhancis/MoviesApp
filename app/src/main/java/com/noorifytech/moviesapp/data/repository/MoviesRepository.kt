package com.noorifytech.moviesapp.data.repository

import androidx.paging.PagedList
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.data.repository.vo.Resource

interface MoviesRepository {
    fun getPopularMovies(): Resource<PagedList<MovieVO>>
    fun getMovieDetails(movieId: Int): Resource<MovieDetailVO>
}