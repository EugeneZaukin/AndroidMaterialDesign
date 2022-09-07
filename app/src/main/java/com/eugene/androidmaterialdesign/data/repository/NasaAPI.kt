package com.eugene.androidmaterialdesign.data.repository

import com.eugene.androidmaterialdesign.data.model.PODServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ) : Call<PODServerResponseData>
}