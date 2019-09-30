package com.noorifytech.moviesapp.common

import android.annotation.SuppressLint
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import java.text.SimpleDateFormat

object MovieMapper {
    fun toMovies(moviesResponse: MoviesListResponse): List<MovieEntity> {
        val movies = moviesResponse.results

        return movies.map {
            MovieEntity(it.id, it.title, it.getPosterFullPath(), moviesResponse.page, it.overview, toTimeStamp(it.releaseDate), it.voteAverage)
        }
    }

    fun toMovieVO(movieEntity: MovieEntity): MovieVO {
        return MovieVO(
            movieEntity.id,
            movieEntity.title,
            movieEntity.imageUrl,
            movieEntity.page,
            movieEntity.overview,
            movieEntity.releaseDate,
            movieEntity.voteAverage
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun toTimeStamp(dateStr: String): Long {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val d = formatter.parse(dateStr)

        return d.time
    }
}