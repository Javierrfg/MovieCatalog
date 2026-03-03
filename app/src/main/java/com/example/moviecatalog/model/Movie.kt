package com.example.moviecatalog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val genre: String,
    val releaseYear: String,
    val isFavorite: Boolean = false
)