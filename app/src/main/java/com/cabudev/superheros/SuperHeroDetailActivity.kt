package com.cabudev.superheros

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.cabudev.superheros.databinding.ActivitySuperHeroDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroDetailActivity : AppCompatActivity() {

    private lateinit var retrofit : Retrofit
    private lateinit var binding : ActivitySuperHeroDetailBinding

    companion object{
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySuperHeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)

        retrofit = getRetrofit()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getSuperHeroInformation(id: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = retrofit.create(ApiService::class.java).getSuperHeroInformation(id)

            if(superHeroDetail.body() != null){
                runOnUiThread{ createUi(superHeroDetail.body()!!)}
            }
        }
    }

    private fun createUi(superHero: SuperHeroDetailResponse) {

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/")
            .addConverterFactory(
                GsonConverterFactory
                .create())
            .build()

    }
}