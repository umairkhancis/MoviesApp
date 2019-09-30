package com.noorifytech.moviesapp.data.repository.vo

import java.text.SimpleDateFormat
import java.util.*

data class MovieDetailVO(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val overview: String,
    val releaseDate: Long,
    val voteAverage: Float
) {
    fun getReleaseDate(): String = SimpleDateFormat("MM/dd/yyyy").format(Date(this.releaseDate))
}