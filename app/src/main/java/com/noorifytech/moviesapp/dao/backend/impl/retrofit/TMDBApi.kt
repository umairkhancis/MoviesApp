package com.noorifytech.moviesapp.dao.backend.impl.retrofit

import com.noorifytech.moviesapp.common.AppConstants.Companion.API_KEY
import com.noorifytech.moviesapp.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.exception.BackendException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @Throws(BackendException::class)
    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Response<MoviesListResponse>
}