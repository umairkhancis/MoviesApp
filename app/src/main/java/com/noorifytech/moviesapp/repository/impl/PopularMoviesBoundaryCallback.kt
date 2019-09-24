package com.noorifytech.moviesapp.repository.impl

import androidx.paging.PagedList
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.dao.backend.dto.ApiSuccessResponse
import com.noorifytech.moviesapp.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.exception.BackendException
import com.noorifytech.moviesapp.vo.MovieVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PopularMoviesBoundaryCallback(
    private val moviesDBDao: MoviesDBDao,
    private val moviesApiDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : PagedList.BoundaryCallback<MovieVO>(), CoroutineScope {

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private var isInProgress: Boolean = false

    override fun onZeroItemsLoaded() {
        println("PopularMoviesBoundaryCallback.onZeroItemsLoaded")
        queryAndSave()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieVO) {
        println("PopularMoviesBoundaryCallback.onItemAtEndLoaded")
        queryAndSave(itemAtEnd)
    }

    private fun queryAndSave(itemAtEnd: MovieVO? = null) {
        println("PopularMoviesBoundaryCallback.queryAndSave")
        val nextPage = itemAtEnd?.page?.plus(1) ?: 1

        if (isInProgress) return

        val routine = launch {
            isInProgress = true

            try {
                val response =
                    moviesApiDao.getPopularMovies(nextPage)

                if (response is ApiSuccessResponse) {
                    println("PopularMoviesBoundaryCallback.queryAndSave:ApiSuccessResponse")
                    val movieEntities = movieMapper.toMovies(response.body)
                    movieEntities.map { moviesDBDao.insert(it) }
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