package com.eugene.androidmaterialdesign.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugene.androidmaterialdesign.BuildConfig
import com.eugene.androidmaterialdesign.data.repository.NetworkRepository
import com.eugene.androidmaterialdesign.domain.PictureOfTheDayData
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(private val nasaRepository: NetworkRepository) : ViewModel() {

    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayData> = MutableLiveData()

    fun getData(date: String): LiveData<PictureOfTheDayData> {
        sendServerRequest(date)
        return liveDataForViewToObserve
    }

    private fun sendServerRequest(date: String) {
        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pictureOfTheDay = nasaRepository.getPictureOfTheDay(apiKey, date)
                liveDataForViewToObserve.value = PictureOfTheDayData.Success(pictureOfTheDay)
            } catch (e: Exception) {

            }
        }


        if (apiKey.isBlank()) {
            PictureOfTheDayData.Error(Throwable("You need API key"))
        } else {
//                retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey, date)
//                    .enqueue(object : Callback<NasaInfo> {
//                        override fun onResponse(
//                            call: Call<NasaInfo>,
//                            response: Response<NasaInfo>
//                        ) {
//                            if (response.isSuccessful && response.body() != null) {
//                                liveDataForViewToObserve.value =
//                                    PictureOfTheDayData.Success(response.body()!!)
//                            } else {
//                                val message = response.message()
//                                if (message.isNullOrEmpty()) {
//                                    liveDataForViewToObserve.value =
//                                        PictureOfTheDayData.Error(Throwable("Unidentified error"))
//                                } else {
//                                    liveDataForViewToObserve.value =
//                                        PictureOfTheDayData.Error(Throwable(message))
//                                }
//                            }
//                        }

//                        override fun onFailure(
//                            call: Call<NasaInfo>, t:
//                            Throwable
//                        ) {
//                            liveDataForViewToObserve.value = PictureOfTheDayData.Error(t)
//                        }
//                    })
        }
    }
}