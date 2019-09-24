package com.noorifytech.moviesapp.factory

import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.noorifytech.moviesapp.activity.MoviesListActivity
import com.noorifytech.moviesapp.common.MovieMapper
import com.noorifytech.moviesapp.dao.backend.impl.MoviesBackendDaoImpl
import com.noorifytech.moviesapp.dao.backend.impl.retrofit.RetrofitFactory
import com.noorifytech.moviesapp.dao.backend.impl.retrofit.TMDBApi
import com.noorifytech.moviesapp.dao.db.RoomDB
import com.noorifytech.moviesapp.repository.impl.MoviesRepositoryImpl
import com.noorifytech.moviesapp.repository.impl.PopularMoviesBoundaryCallback
import com.noorifytech.moviesapp.viewmodel.MoviesListViewModel


object MoviesListFactory {
    fun getViewModel(movieListActivity: MoviesListActivity): MoviesListViewModel {
        val db = Room.databaseBuilder(movieListActivity,
            RoomDB::class.java, "movies_app")
            .build()

        val backendTMDBApi =
            RetrofitFactory.getDefaultRetrofit()
                .create(TMDBApi::class.java)

        val moviesBackendDao = MoviesBackendDaoImpl(backendTMDBApi)

        val moviesRepo = MoviesRepositoryImpl(db.moviesDao(), MovieMapper)

        val viewModelFactory = MoviesListViewModelFactory(moviesRepo,
            PopularMoviesBoundaryCallback(db.moviesDao(), moviesBackendDao, MovieMapper)
        )

        return ViewModelProviders.of(movieListActivity, viewModelFactory)
            .get(MoviesListViewModel::class.java)
    }
}