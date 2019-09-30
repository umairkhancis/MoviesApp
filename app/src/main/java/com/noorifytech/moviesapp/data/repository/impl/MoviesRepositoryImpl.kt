package com.noorifytech.moviesapp.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.backend.dto.ApiSuccessResponse
import com.noorifytech.moviesapp.data.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.data.repository.MoviesRepository
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.data.repository.vo.NetworkStatus
import com.noorifytech.moviesapp.data.repository.vo.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesRepositoryImpl(
    private val moviesDBDao: MoviesDBDao,
    private val moviesBackendDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : MoviesRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + handler

    private var job = Job()

    private var handler = CoroutineExceptionHandler { _, throwable ->
        println("${throwable.message}")
    }

    override fun getPopularMovies(): Resource<PagedList<MovieVO>> {

        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PRE_FETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

        val networkStatus: MutableLiveData<NetworkStatus> = MutableLiveData()
        val boundaryCallback =
            PopularMoviesBoundaryCallback(moviesDBDao, moviesBackendDao, MovieMapper, networkStatus)
        val factory = moviesDBDao.getPopularMovies().map { movieMapper.toMovieVO(it) }

        val livePagedListBuilder: LivePagedListBuilder<Int, MovieVO> =
            LivePagedListBuilder(factory, config)
                .setBoundaryCallback(boundaryCallback)

        val liveData: LiveData<PagedList<MovieVO>> = livePagedListBuilder.build()

        return Resource(
            data = liveData,
            networkStatus = networkStatus,
            msg = ""
        )
    }

    override fun getMovieDetails(movieId: Int): Resource<MovieDetailVO> {

        val networkStatus: MutableLiveData<NetworkStatus> = MutableLiveData()

        launch(Dispatchers.IO) {
            networkStatus.postValue(NetworkStatus.LOADING)

            val response = moviesBackendDao.getMovieDetails(movieId)

            if (response is ApiSuccessResponse) {
                val movieEntity = movieMapper.toMovieDetailEntity(response.body)
                moviesDBDao.insert(movieEntity)
                networkStatus.postValue(NetworkStatus.SUCCESS)
            } else {
                networkStatus.postValue(NetworkStatus.FAILED)
            }
        }

        val liveData: LiveData<MovieDetailVO>? =
            Transformations.map(moviesDBDao.getMovieDetails(movieId)) {
                it?.let { movieMapper.toMovieDetailVO(it) }
            }

        return Resource(
            data = liveData,
            networkStatus = networkStatus,
            msg = ""
        )
    }

    companion object {
        const val PAGE_SIZE = 40
        const val PRE_FETCH_DISTANCE = 10
    }
}