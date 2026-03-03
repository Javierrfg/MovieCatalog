package com.example.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviecatalog.model.Movie

@Dao
interface MovieDao {
    // Inserta una película de forma asíncrona (Coroutine)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    // Obtiene todas las películas y avisa a la UI si hay cambios (LiveData)
    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<Movie>>

    // Borra todas las películas
    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()
}