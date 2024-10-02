package com.cabudev.superheros

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitConection {
    private final val apiUrl = "https://superheroapi.com/"

    fun getRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(apiUrl)
            .addConverterFactory(
                GsonConverterFactory
                .create())
            .build()
    }
}