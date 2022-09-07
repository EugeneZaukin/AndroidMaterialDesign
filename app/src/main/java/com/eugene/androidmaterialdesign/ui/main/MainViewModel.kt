package com.eugene.androidmaterialdesign.ui.main

import androidx.lifecycle.*
import com.eugene.androidmaterialdesign.BuildConfig
import com.eugene.androidmaterialdesign.data.repository.NetworkRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val EMPTY_STRING = ""

class MainViewModel @Inject constructor(private val nasaRepository: NetworkRepository) : ViewModel() {
    private val _titleText = MutableStateFlow(EMPTY_STRING)
    val titleText get() = _titleText.asStateFlow()

    private val _descriptionText = MutableStateFlow(EMPTY_STRING)
    val descriptionText get() = _descriptionText.asStateFlow()

    private val _loadingState = MutableStateFlow(true)
    val loadingState get() = _loadingState.asStateFlow()

    private val _error = MutableSharedFlow<String>(0, 1, BufferOverflow.DROP_OLDEST)
    val error get() = _error.asSharedFlow()

    fun sendServerRequest(date: String) {
        _loadingState.tryEmit(true)
        val apiKey: String = BuildConfig.NASA_API_KEY

        if (apiKey.isBlank()) {
            _error.tryEmit("You need API key")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pictureOfTheDay = nasaRepository.getPictureOfTheDay(apiKey, date)
                _titleText.tryEmit(pictureOfTheDay.title)
                _descriptionText.tryEmit(pictureOfTheDay.explanation)
            } catch (e: Exception) {
                _error.tryEmit(e.message ?: EMPTY_STRING)
            }
        }
    }
}