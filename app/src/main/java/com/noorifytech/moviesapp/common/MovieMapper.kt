package com.noorifytech.moviesapp.common

import android.annotation.SuppressLint
import com.noorifytech.moviesapp.data.dao.backend.dto.MovieDto
import com.noorifytech.moviesapp.data.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.data.dao.db.entity.MovieDetailEntity
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.data.repository.vo.MovieDetailVO
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import java.text.SimpleDateFormat

object MovieMapper {
    fun toMovies(moviesResponse: MoviesListResponse): List<MovieEntity> {
        val movies = moviesResponse.results
        return movies.map { toMovieEntity(it) }
    }

    fun toMovieEntity(movieDto: MovieDto): MovieEntity {
        return MovieEntity(
            movieDto.id,
            movieDto.title,
            movieDto.getPosterFullPath(),
            movieDto.page
        )
    }

    fun toMovieDetailEntity(movieDto: MovieDto): MovieDetailEntity {
        return MovieDetailEntity(
            movieDto.id,
            movieDto.title,
            movieDto.getPosterFullPath(),
            movieDto.overview,
            toTimeStamp(movieDto.releaseDate),
            movieDto.voteAverage
        )
    }

    fun toMovieVO(movieEntity: MovieEntity): MovieVO {
        return MovieVO(
            movieEntity.id,
            movieEntity.title,
            movieEntity.imageUrl,
            movieEntity.page
        )
    }

    fun toMovieDetailVO(movieDetailEntity: MovieDetailEntity): MovieDetailVO {
        return MovieDetailVO(
            movieDetailEntity.id,
            movieDetailEntity.title,
            movieDetailEntity.imageUrl,
            movieDetailEntity.overview,
            movieDetailEntity.releaseDate,
            movieDetailEntity.voteAverage
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun toTimeStamp(dateStr: String): Long {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val d = formatter.parse(dateStr)

        return d.time
    }
}