package com.eugene.androidmaterialdesign.ui.viewpager

import androidx.lifecycle.ViewModel
import com.eugene.androidmaterialdesign.BuildConfig
import com.eugene.androidmaterialdesign.ui.main.PODRetrofitImpl
import com.eugene.androidmaterialdesign.ui.main.PODServerResponseData
import com.eugene.androidmaterialdesign.ui.main.PictureOfTheDayData
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DayFragmentViewModel : ViewModel() {
    private val _urlString = MutableStateFlow("")
    val urlString get() = _urlString.asStateFlow()

    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()

    fun getPicture(date: String) {
        val apiKey: String = BuildConfig.NASA_API_KEY


        retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey, date).enqueue(object : Callback<PODServerResponseData> {
            override fun onResponse(
                call: Call<PODServerResponseData>,
                response: Response<PODServerResponseData>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    _urlString.tryEmit(response.body()!!.url!!)
                }
            }

            override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {

            }

        })


    }



}