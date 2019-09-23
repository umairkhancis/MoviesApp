package com.noorifytech.moviesapp.dao.api

import androidx.lifecycle.LiveData
import com.noorifytech.moviesapp.common.AppConstants.Companion.API_KEY
import com.noorifytech.moviesapp.dao.api.dto.ApiResponse
import com.noorifytech.moviesapp.dao.api.dto.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular?api_key=$API_KEY")
    fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): LiveData<ApiResponse<MoviesListResponse>>
}