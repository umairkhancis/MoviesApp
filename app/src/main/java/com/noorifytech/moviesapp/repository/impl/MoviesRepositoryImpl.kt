package com.noorifytech.moviesapp.repository.impl

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesDBDao: MoviesDBDao,
    private val moviesBackendDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : MoviesRepository {

    override fun getPopularMovies(): LiveData<PagedList<MovieEntity>> {
//        val config = PagedList.Config.Builder()
//            .setPageSize(PAGE_SIZE)
//            .setInitialLoadSizeHint(PAGE_SIZE)
//            .setPrefetchDistance(PRE_FETCH_DISTANCE)
//            .setEnablePlaceholders(false)
//            .build()

        val boundaryCallback = PopularMoviesBoundaryCallback(moviesDBDao, moviesBackendDao, MovieMapper)

        val popularMoviesDSFactory = moviesDBDao.getPopularMovies()

        val popularMovies = popularMoviesDSFactory.toLiveData(
            pageSize = PAGE_SIZE,
            boundaryCallback = boundaryCallback)

        return popularMovies
    }

    companion object {
        const val PAGE_SIZE = 20
        const val PRE_FETCH_DISTANCE = 10
    }
}