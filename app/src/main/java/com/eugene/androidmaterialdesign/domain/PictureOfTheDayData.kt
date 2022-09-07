package com.eugene.androidmaterialdesign.domain

import com.eugene.androidmaterialdesign.data.model.NasaInfo

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: NasaInfo) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}