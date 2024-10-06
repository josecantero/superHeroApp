package com.cabudev.superheros

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.cabudev.superheros.databinding.ActivitySuperHeroDetailBinding
import com.squareup.picasso.Picasso
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
        retrofit = getRetrofit()
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



    private fun createUi(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHeroDetailImage)
        binding.tvDetailSuperHeroName.text = superHero.name
        prepareStats(superHero.powerstats)
    }

    private fun prepareStats(powerStats: PowerStatsResponse) {

        updateParams(binding.vSpInteligence, powerStats.intelligence)
        updateParams(binding.vSpStrengt, powerStats.strength)
        updateParams(binding.vSpSpeed, powerStats.speed)
        updateParams(binding.vSpDurability, powerStats.durability)
        updateParams(binding.vSpPower, powerStats.power)
        updateParams(binding.vSpCombat, powerStats.combat)
    }

    private fun updateParams(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pixelToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pixelToDp(pixel: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel, resources.displayMetrics).toInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/")
            .addConverterFactory(
                GsonConverterFactory
                .create())
            .build()

    }

    private fun getSuperHeroInformation(id: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = retrofit.create(ApiService::class.java).getSuperHeroInformation(id)

            if(superHeroDetail.body() != null){
                runOnUiThread{ createUi(superHeroDetail.body()!!)}
            }
        }
    }
}