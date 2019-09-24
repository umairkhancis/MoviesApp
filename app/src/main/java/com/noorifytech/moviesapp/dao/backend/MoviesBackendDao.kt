package com.noorifytech.moviesapp.dao.backend

import com.noorifytech.moviesapp.dao.backend.dto.ApiResponse
import com.noorifytech.moviesapp.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.exception.BackendException

interface MoviesBackendDao {
    @Throws(BackendException::class)
    suspend fun getPopularMovies(page: Int = 1): ApiResponse<MoviesListResponse>
}