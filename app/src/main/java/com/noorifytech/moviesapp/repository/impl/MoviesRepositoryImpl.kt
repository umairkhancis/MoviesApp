package com.noorifytech.moviesapp.repository.impl

import androidx.paging.DataSource
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.repository.MoviesRepository
import com.noorifytech.moviesapp.vo.MovieVO

class MoviesRepositoryImpl(
    private val moviesDBDao: MoviesDBDao,
    private val movieMapper: MovieMapper
) : MoviesRepository {

    override fun getPopularMovies(): DataSource.Factory<Int, MovieVO> {
        return moviesDBDao.getPopularMovies().map { movieMapper.toMovieVO(it) }
    }
}