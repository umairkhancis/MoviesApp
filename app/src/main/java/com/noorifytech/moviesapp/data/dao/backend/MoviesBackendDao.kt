package com.noorifytech.moviesapp.data.dao.backend

import com.noorifytech.moviesapp.data.dao.backend.dto.ApiResponse
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse

interface MoviesBackendDao {
    suspend fun getPopularMovies(page: Int = 1): ApiResponse<MoviesListResponse>
}