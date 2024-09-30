package com.cabudev.superheros

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/12462fcc634f133a21481be959dbcd36/search/{name}")
    suspend fun getSuperHeroes(@Path("name") SuperHeroName:String): Response<SuperHeroDataResponse>
}