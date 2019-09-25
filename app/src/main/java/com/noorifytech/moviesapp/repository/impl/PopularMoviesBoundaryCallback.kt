package com.noorifytech.moviesapp.repository.impl

import androidx.paging.PagedList
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.dao.backend.dto.ApiSuccessResponse
import com.noorifytech.moviesapp.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.exception.BackendException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PopularMoviesBoundaryCallback(
    private val moviesDBDao: MoviesDBDao,
    private val moviesApiDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : PagedList.BoundaryCallback<MovieEntity>(), CoroutineScope {

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var isInProgress: Boolean = false

    override fun onZeroItemsLoaded() {
        println("PopularMoviesBoundaryCallback.onZeroItemsLoaded")
        queryAndSave()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieEntity) {
        println("PopularMoviesBoundaryCallback.onItemAtEndLoaded")
        queryAndSave(itemAtEnd)
    }

    private fun queryAndSave(itemAtEnd: MovieEntity? = null) {
        println("PopularMoviesBoundaryCallback.queryAndSave")
        val nextPage = itemAtEnd?.page?.plus(1) ?: 1

        if (isInProgress) return

        launch(Dispatchers.IO) {
            isInProgress = true

            try {
                val response =
                    moviesApiDao.getPopularMovies(nextPage)

                if (response is ApiSuccessResponse) {
                    println("PopularMoviesBoundaryCallback.queryAndSave:ApiSuccessResponse")
                    val movieEntities = movieMapper.toMovies(response.body)
                    moviesDBDao.insert(movieEntities)
                    println("PopularMoviesBoundaryCallback.queryAndSave:Saved::::")
                } else {
                    println("return error to the view")
                }
            } catch (ex: BackendException) {
                println("BackendException: ${ex.msg}")
            } finally {
                isInProgress = false
            }
        }

    }
}