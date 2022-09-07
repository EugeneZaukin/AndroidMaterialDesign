package com.eugene.androidmaterialdesign.ui.main.viewpager

import androidx.lifecycle.ViewModel
import com.eugene.androidmaterialdesign.BuildConfig
import com.eugene.androidmaterialdesign.data.repository.NetworkRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DayFragmentViewModel @Inject constructor(private val nasaRepository: NetworkRepository): ViewModel() {
    private val _urlString = MutableStateFlow("")
    val urlString get() = _urlString.asStateFlow()

    fun getPicture(date: String) {
        val apiKey: String = BuildConfig.NASA_API_KEY


//        retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey, date).enqueue(object : Callback<NasaInfo> {
//            override fun onResponse(
//                call: Call<NasaInfo>,
//                response: Response<NasaInfo>
//            ) {
//                if (response.isSuccessful && response.body() != null) {
//                    _urlString.tryEmit(response.body()!!.url!!)
//                }
//            }
//
//            override fun onFailure(call: Call<NasaInfo>, t: Throwable) {
//
//            }
//
//        })


    }



}