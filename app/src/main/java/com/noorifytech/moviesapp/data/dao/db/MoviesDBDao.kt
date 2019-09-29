package com.noorifytech.moviesapp.data.dao.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noorifytech.moviesapp.data.dao.db.entity.MovieEntity

/**
 * Interface for database access for User related operations.
 */
@Dao()
interface MoviesDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieEntity>)

    @Query("SELECT * FROM Movies ORDER BY page ASC")
    fun getPopularMovies(): DataSource.Factory<Int, MovieEntity>
}