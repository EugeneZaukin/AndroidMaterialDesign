package com.eugene.androidmaterialdesign.ui.main.viewpager

import androidx.lifecycle.ViewModel
import com.eugene.androidmaterialdesign.BuildConfig
import com.eugene.androidmaterialdesign.data.repository.NetworkRepositoryImpl
import com.eugene.androidmaterialdesign.data.model.NasaInfo
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DayFragmentViewModel : ViewModel() {
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