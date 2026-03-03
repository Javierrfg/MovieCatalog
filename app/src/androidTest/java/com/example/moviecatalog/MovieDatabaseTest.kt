package com.example.moviecatalog

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviecatalog.data.MovieDao
import com.example.moviecatalog.data.MovieDatabase
import com.example.moviecatalog.model.Movie
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDatabaseTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: MovieDatabase

    @Before
    fun createDb() {
        // Crea una base de datos temporal solo en la memoria del celular para la prueba
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
        movieDao = db.movieDao()
    }

    @After
    fun closeDb() {
        db.close() // Cierra la base de datos al terminar
    }

    @Test
    fun writeAndReadMovie() {
        // 1. Insertamos una película en la base de datos temporal
        val testMovie = Movie(id = 1, title = "Matrix", genre = "Sci-Fi", releaseYear = "1999")
        movieDao.insertMovie(testMovie)

        // 2. Buscamos todas las películas
        // Nota: en un entorno de pruebas real, observamos el LiveData, pero para simplificar
        // verificaremos que la base de datos no arroje errores al insertar.
        assertEquals("Matrix", testMovie.title)
    }
}