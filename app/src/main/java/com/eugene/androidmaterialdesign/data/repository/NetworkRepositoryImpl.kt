package com.eugene.androidmaterialdesign.data.repository

import com.eugene.androidmaterialdesign.data.model.NasaInfo
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val retrofitApi: NasaAPI) : NetworkRepository {

    override suspend fun getPictureOfTheDay(apiKey: String, date: String): NasaInfo =
        retrofitApi.getPictureOfTheDay(apiKey, date)
}