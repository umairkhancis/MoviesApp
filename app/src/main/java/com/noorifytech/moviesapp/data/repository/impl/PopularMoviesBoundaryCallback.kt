package com.noorifytech.moviesapp.data.repository.impl

import androidx.paging.PagedList
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.data.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.data.dao.backend.dto.ApiSuccessResponse
import com.noorifytech.moviesapp.data.dao.db.MoviesDBDao
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PopularMoviesBoundaryCallback(
    private val moviesDBDao: MoviesDBDao,
    private val moviesApiDao: MoviesBackendDao,
    private val movieMapper: MovieMapper
) : PagedList.BoundaryCallback<MovieVO>(), CoroutineScope {

    @Volatile
    private var isInProgress: Boolean = false

    override fun onZeroItemsLoaded() {
        queryAndSave()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieVO) {
        queryAndSave(itemAtEnd)
    }

    private fun queryAndSave(itemAtEnd: MovieVO? = null) {
        val nextPage = itemAtEnd?.page?.plus(1) ?: 1

        if (isInProgress) return

        launch(Dispatchers.IO) {
            isInProgress = true

            val response =
                moviesApiDao.getPopularMovies(nextPage)

            if (response is ApiSuccessResponse) {
                val movieEntities = movieMapper.toMovies(response.body)
                moviesDBDao.insert(movieEntities)
            } else {
                println("return error to the view")
            }

            isInProgress = false
        }

    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + handler

    private var job = Job()

    private var handler = CoroutineExceptionHandler { _, throwable ->
        println("${throwable.message}")
    }

}