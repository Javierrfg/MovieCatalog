package com.example.moviecatalog.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.moviecatalog.model.Movie
import com.example.moviecatalog.network.RetrofitClient

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: LiveData<List<Movie>> = movieDao.getAllMovies()

    // Funciones locales sin "suspend"
    fun insert(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    fun deleteAll() {
        movieDao.deleteAll()
    }

    // Funciones de red con "suspend" (Retrofit no tiene el bug de KSP2)
    suspend fun fetchMoviesFromApi() {
        try {
            val response = RetrofitClient.apiService.getMovies(page = 1)

            if (response.isSuccessful) {
                response.body()?.let { moviesFromNetwork ->
                    for (movie in moviesFromNetwork) {
                        movieDao.insertMovie(movie)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error de conexión: ${e.message}")
        }
    }

    suspend fun syncMovieToApi(movie: Movie) {
        try {
            RetrofitClient.apiService.addMovie(authToken = "Bearer MOCK_TOKEN_123", movie = movie)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error al sincronizar: ${e.message}")
        }
    }
}