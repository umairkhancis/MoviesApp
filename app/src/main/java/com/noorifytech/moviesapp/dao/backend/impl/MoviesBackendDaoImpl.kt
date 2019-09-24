package com.noorifytech.moviesapp.dao.backend.impl

import com.noorifytech.moviesapp.dao.backend.MoviesBackendDao
import com.noorifytech.moviesapp.dao.backend.dto.ApiResponse
import com.noorifytech.moviesapp.dao.backend.impl.retrofit.TMDBApi

class MoviesBackendDaoImpl(private val api: TMDBApi) : MoviesBackendDao {

    override suspend fun getPopularMovies(page: Int) =
        ApiResponse.create(api.getPopularMovies(page = "$page"))
}