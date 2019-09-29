package com.noorifytech.moviesapp.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.data.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesDBDao: MoviesDBDao,
    private val moviesBackendDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : MoviesRepository {

    override fun getPopularMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PRE_FETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

        val boundaryCallback = PopularMoviesBoundaryCallback(moviesDBDao, moviesBackendDao, MovieMapper)
        val factory = moviesDBDao.getPopularMovies()

        val livePagedListBuilder: LivePagedListBuilder<Int, MovieEntity> =
            LivePagedListBuilder(factory, config)
            .setBoundaryCallback(boundaryCallback)

        return livePagedListBuilder.build()
    }

    companion object {
        const val PAGE_SIZE = 40
        const val PRE_FETCH_DISTANCE = 10
    }
}