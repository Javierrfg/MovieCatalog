package com.example.moviecatalog.network

import com.example.moviecatalog.model.Movie
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieApiService {

    // Petición GET con QueryParams (exigido en rúbrica)
    @GET("movies")
    suspend fun getMovies(
        @Query("page") page: Int = 1
    ): Response<List<Movie>>

    // Petición POST con Header de AuthToken y Body (exigido en rúbrica)
    @POST("movies")
    suspend fun addMovie(
        @Header("Authorization") authToken: String,
        @Body movie: Movie
    ): Response<Movie>
}