package com.noorifytech.moviesapp.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.noorifytech.moviesapp.common.AppExecutors
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.dao.api.TMDBService
import com.noorifytech.moviesapp.dao.api.dto.MoviesListResponse
import com.noorifytech.moviesapp.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.repository.MoviesRepository
import com.noorifytech.moviesapp.repository.NetworkBoundResource
import com.noorifytech.moviesapp.vo.MoviesVO
import com.noorifytech.moviesapp.vo.Resource

class MoviesRepositoryImpl(
    private val moviesDBDao: MoviesDBDao,
    private val moviesApiDao: TMDBService,
    private val movieMapper: MovieMapper,
    val appExecutors: AppExecutors
) : MoviesRepository {

    override fun getMoviesList(): LiveData<Resource<List<MoviesVO>>> {
        return object : NetworkBoundResource<List<MoviesVO>, MoviesListResponse>(appExecutors) {
            override fun saveCallResult(response: MoviesListResponse) {
                val movies = movieMapper.toMovies(response)
                movies.map { moviesDBDao.insert(it) }
            }

            override fun shouldFetch(data: List<MoviesVO>?) = data == null || data.isEmpty()

            override fun loadFromDb() = Transformations.map(moviesDBDao.getPopularMovies()) { movieMapper.toMovies(it) }

            override fun createCall() = moviesApiDao.getPopularMovies()
        }.asLiveData()
    }
}