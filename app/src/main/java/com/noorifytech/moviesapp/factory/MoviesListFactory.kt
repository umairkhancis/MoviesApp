package com.noorifytech.moviesapp.factory

import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.noorifytech.moviesapp.activity.MoviesListActivity
import com.noorifytech.moviesapp.common.AppExecutors
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.dao.api.RetrofitFactory
import com.noorifytech.moviesapp.dao.api.TMDBService
import com.noorifytech.moviesapp.dao.db.RoomDB
import com.noorifytech.moviesapp.repository.impl.MoviesRepositoryImpl
import com.noorifytech.moviesapp.viewmodel.MoviesListViewModel


object MoviesListFactory {
    fun getViewModel(movieListActivity: MoviesListActivity): MoviesListViewModel {
        val db = Room.databaseBuilder(movieListActivity,
            RoomDB::class.java, "movies_app")
            .build()

        val backendTMDBService =
            RetrofitFactory.getDefaultRetrofit()
                .create(TMDBService::class.java)

        val moviesRepo =
            MoviesRepositoryImpl(db.moviesDao(), backendTMDBService, MovieMapper, AppExecutors())

        val viewModelFactory = MoviesListViewModelFactory(moviesRepo)

        return ViewModelProviders.of(movieListActivity, viewModelFactory)
            .get(MoviesListViewModel::class.java)
    }
}