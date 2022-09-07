package com.eugene.androidmaterialdesign.data.repository

import com.eugene.androidmaterialdesign.data.model.NasaInfo

interface NetworkRepository {
    suspend fun getPictureOfTheDay(apiKey: String, date: String): NasaInfo
}