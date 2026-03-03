package com.example.moviecatalog.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalog.databinding.ActivityContactBinding
import com.example.moviecatalog.model.Movie
import com.example.moviecatalog.viewmodel.MovieViewModel

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveMovie.setOnClickListener {
            val title = binding.etMovieTitle.text.toString()
            val genre = binding.etMovieGenre.text.toString()
            val year = binding.etMovieYear.text.toString()

            if (title.isNotEmpty() && genre.isNotEmpty() && year.isNotEmpty()) {
                val newMovie = Movie(title = title, genre = genre, releaseYear = year)
                movieViewModel.insert(newMovie)

                Toast.makeText(this, "Película guardada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}