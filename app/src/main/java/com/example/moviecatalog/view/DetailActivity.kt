package com.example.moviecatalog.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalog.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("MOVIE_TITLE")
        val genre = intent.getStringExtra("MOVIE_GENRE")
        val year = intent.getStringExtra("MOVIE_YEAR")

        binding.tvDetailTitle.text = title
        binding.tvDetailGenre.text = "Género: $genre"
        binding.tvDetailYear.text = "Año: $year"
    }
}