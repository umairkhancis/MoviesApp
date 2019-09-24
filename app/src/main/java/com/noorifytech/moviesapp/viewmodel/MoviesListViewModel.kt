package com.noorifytech.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.noorifytech.moviesapp.repository.MoviesRepository
import com.noorifytech.moviesapp.vo.MovieVO

class MoviesListViewModel(
    moviesRepository: MoviesRepository,
    popularMoviesBoundaryCB: PagedList.BoundaryCallback<MovieVO>
) : ViewModel() {

    private val popularMovies: LiveData<PagedList<MovieVO>>

    init {
        val popularMoviesDSFactory =
            moviesRepository.getPopularMovies()

        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PRE_FETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()

        popularMovies = LivePagedListBuilder(popularMoviesDSFactory, config)
            .setBoundaryCallback(popularMoviesBoundaryCB)
            .build()
    }

    fun getPopularMovies(): LiveData<PagedList<MovieVO>> {
        return popularMovies
    }

    companion object {
        const val PAGE_SIZE = 5
        const val PRE_FETCH_DISTANCE = 2
    }
}