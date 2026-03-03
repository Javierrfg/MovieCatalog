package com.example.moviecatalog.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.moviecatalog.MainActivity
import com.example.moviecatalog.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Usamos lifecycleScope para lanzar una corrutina (exigencia de la rúbrica)
        lifecycleScope.launch {
            delay(2500) // Espera 2.5 segundos (2500 milisegundos)

            // Navegamos hacia el MainActivity
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)

            // Cerramos el Splash para que el usuario no pueda volver a él presionando el botón "Atrás"
            finish()
        }
    }
}