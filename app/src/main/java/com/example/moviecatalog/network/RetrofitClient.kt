package com.example.moviecatalog.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // URL base de prueba. En un entorno real, aquí iría la URL de tu servidor
    private const val BASE_URL = "https://my-json-server.typicode.com/demo/"

    val apiService: MovieApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Esto cumple el requisito de "Mapeo de JSON a data class"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }
}