package com.noorifytech.moviesapp.dao.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noorifytech.moviesapp.dao.db.entity.Movie

/**
 * Interface for database access for User related operations.
 */
@Dao()
interface MoviesDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getPopularMovies(): LiveData<List<Movie>>
}