package com.example.moviecatalog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.data.MovieDatabase
import com.example.moviecatalog.data.MovieRepository
import com.example.moviecatalog.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    val allMovies: LiveData<List<Movie>>
    private val repository: MovieRepository

    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
        allMovies = repository.allMovies

        // Al iniciar la app, intentamos descargar películas de internet
        fetchFromApi()
    }

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        // Guarda en la base de datos local
        repository.insert(movie)
        // Simula la subida a internet
        repository.syncMovieToApi(movie)
    }

    private fun fetchFromApi() = viewModelScope.launch(Dispatchers.IO) {
        repository.fetchMoviesFromApi()
    }
}