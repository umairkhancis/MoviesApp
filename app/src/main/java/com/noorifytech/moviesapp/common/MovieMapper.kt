package com.noorifytech.moviesapp.common

import com.noorifytech.moviesapp.dao.backend.dto.MoviesListResponse
import com.noorifytech.moviesapp.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.vo.MovieVO

object MovieMapper {
    fun toMovies(moviesResponse: MoviesListResponse): List<MovieEntity> {
        val movies = moviesResponse.results

        return movies.map {
            MovieEntity(it.id, it.title, it.getPosterFullPath(), moviesResponse.page)
        }
    }

    fun toMovieVO(movieEntity: MovieEntity): MovieVO {
        return MovieVO(
            movieEntity.id,
            movieEntity.title,
            movieEntity.posterPath,
            movieEntity.page
        )
    }
}