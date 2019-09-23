package com.noorifytech.moviesapp.common

import com.noorifytech.moviesapp.dao.api.dto.MoviesListResponse
import com.noorifytech.moviesapp.dao.db.entity.Movie
import com.noorifytech.moviesapp.vo.MoviesVO

object MovieMapper {
    fun toMovies(moviesResponse: MoviesListResponse): List<Movie> {
        val movies = moviesResponse.results
        return movies.map { Movie(it.id, it.title, it.getPosterFullPath()) }
    }

    fun toMovies(movies: List<Movie>): List<MoviesVO> {
        return movies.map { MoviesVO(it.id, it.title, it.posterPath) }
    }
}