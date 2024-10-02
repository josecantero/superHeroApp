package com.cabudev.superheros

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name:String,
    @SerializedName("powerstats") val powerstats:PowerStatsResponse
)

data class PowerStatsResponse(
    @SerializedName("inteligence") val inteligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val speed:String,
    @SerializedName("durability") val durability:String,
    @SerializedName("power") val power:String,
    @SerializedName("combat") val combat:String,
)