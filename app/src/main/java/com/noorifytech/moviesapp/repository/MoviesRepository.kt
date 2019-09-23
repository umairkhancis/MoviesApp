package com.noorifytech.moviesapp.repository

import androidx.lifecycle.LiveData
import com.noorifytech.moviesapp.vo.MoviesVO
import com.noorifytech.moviesapp.vo.Resource

interface MoviesRepository {
    fun getMoviesList(): LiveData<Resource<List<MoviesVO>>>
}