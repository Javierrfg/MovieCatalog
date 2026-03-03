package com.example.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecatalog.model.Movie

@Dao
interface MovieDao {
    // Le quitamos el "suspend" para evitar el error de KSP2
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movie_table")
    fun deleteAll()
}