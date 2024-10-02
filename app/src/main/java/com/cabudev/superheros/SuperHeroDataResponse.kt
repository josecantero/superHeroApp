package com.cabudev.superheros

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response:String,
    @SerializedName("results") val superHeroes:List<SuperHeroItemResponse>
    )

data class SuperHeroItemResponse(
    @SerializedName("id") val superheroid:String,
    @SerializedName("name") val name:String,
    @SerializedName("image") val image:SuperHeroImageResponse
)

data class SuperHeroImageResponse(@SerializedName("url") val url:String)