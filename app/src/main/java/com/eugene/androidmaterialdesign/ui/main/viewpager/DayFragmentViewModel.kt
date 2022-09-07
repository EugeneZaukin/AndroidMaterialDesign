package com.eugene.androidmaterialdesign.ui.main.viewpager

import androidx.lifecycle.*
import com.eugene.androidmaterialdesign.BuildConfig
import com.eugene.androidmaterialdesign.data.repository.NetworkRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import javax.inject.Inject

class DayFragmentViewModel @Inject constructor(private val nasaRepository: NetworkRepository): ViewModel() {
    private val _urlString = MutableStateFlow("")
    val urlString get() = _urlString.asStateFlow()

    fun getPicture(date: String) {
        val apiKey: String = BuildConfig.NASA_API_KEY


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pictureOfTheDay = nasaRepository.getPictureOfTheDay(apiKey, date)
                _urlString.tryEmit(pictureOfTheDay.url)
            } catch (e: Exception) {

            }
        }
    }
}