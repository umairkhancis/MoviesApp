package com.noorifytech.moviesapp.data.dao.backend.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("page")
    val page: Int,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Float
) {

    fun getPosterFullPath(): String = "http://image.tmdb.org/t/p/w185/$posterPath"
}