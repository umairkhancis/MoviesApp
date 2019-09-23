package com.noorifytech.moviesapp.dao.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noorifytech.moviesapp.dao.db.entity.Movie

/**
 * Movies database description.
 */
@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun moviesDao(): MoviesDBDao
}