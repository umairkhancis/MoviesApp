package com.noorifytech.moviesapp.dao.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noorifytech.moviesapp.dao.db.entity.MovieEntity

/**
 * Movies database description.
 */
@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun moviesDao(): MoviesDBDao
}