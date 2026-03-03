package com.example.moviecatalog

import com.example.moviecatalog.data.MovieDao
import com.example.moviecatalog.data.MovieRepository
import com.example.moviecatalog.model.Movie
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MovieRepositoryTest {

    private lateinit var repository: MovieRepository
    private lateinit var mockDao: MovieDao

    @Before
    fun setup() {
        // Mockito crea una versión "falsa" de tu DAO para no afectar la base de datos real
        mockDao = mock(MovieDao::class.java)
        repository = MovieRepository(mockDao)
    }

    @Test
    fun insertMovie_callsDaoInsert() = runTest {
        // Creamos una película de prueba
        val testMovie = Movie(title = "Inception", genre = "Sci-Fi", releaseYear = "2010")

        // Ejecutamos la función de insertar
        repository.insert(testMovie)

        // Verificamos con Mockito que el DAO haya recibido la orden de insertar
        verify(mockDao).insertMovie(testMovie)
    }
}