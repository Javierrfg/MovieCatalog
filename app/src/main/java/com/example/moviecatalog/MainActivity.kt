package com.example.moviecatalog

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.databinding.ActivityMainBinding
import com.example.moviecatalog.view.ContactActivity
import com.example.moviecatalog.view.DetailActivity
import com.example.moviecatalog.view.MovieAdapter
import com.example.moviecatalog.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    // Inicializamos ViewBinding y ViewModel (aquí está corregida la línea sin el texto extra)
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el RecyclerView
        val adapter = MovieAdapter { movie ->
            // Al hacer clic en una película, abrimos la pantalla de detalles
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("MOVIE_TITLE", movie.title)
            intent.putExtra("MOVIE_GENRE", movie.genre)
            intent.putExtra("MOVIE_YEAR", movie.releaseYear)
            startActivity(intent)
        }

        binding.recyclerViewMovies.adapter = adapter
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this)

        // Observar los datos de Room (LiveData)
        movieViewModel.allMovies.observe(this) { movies ->
            movies?.let { adapter.setMovies(it) }
        }

        // Configurar el botón flotante para ir a la pantalla de agregar
        binding.fabAddMovie.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }
}