package com.noorifytech.moviesapp.data.dao.backend.dto

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val results: List<MovieDto>
)

data class MovieDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String
) {

    fun getPosterFullPath(): String = "http://image.tmdb.org/t/p/w185/$posterPath"
}
