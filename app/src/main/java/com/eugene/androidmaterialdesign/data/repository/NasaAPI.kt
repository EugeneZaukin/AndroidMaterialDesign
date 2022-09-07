package com.eugene.androidmaterialdesign.data.repository

import com.eugene.androidmaterialdesign.data.model.NasaInfo
import retrofit2.http.*

interface NasaAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ) : NasaInfo
}