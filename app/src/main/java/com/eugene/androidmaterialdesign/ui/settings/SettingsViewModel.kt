package com.eugene.androidmaterialdesign.ui.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.lifecycle.*
import com.eugene.androidmaterialdesign.R
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val MARS_THEME = 0
const val SPACE_THEME = 1
const val APP_THEME = "APP_THEME"

class SettingsViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) : ViewModel() {
    private val _themeByClick = MutableSharedFlow<Int>(0, 1, BufferOverflow.DROP_LATEST)
    val themeByClick get() = _themeByClick.asSharedFlow()

    private var themeForInit = R.style.MarsTheme
    private val keyOfTheme = intPreferencesKey(APP_THEME)

    init {
        loadThemeFromPref()
    }

    private fun loadThemeFromPref() {
        viewModelScope.launch {
            dataStore.data
                .map { it[keyOfTheme] ?: SPACE_THEME }
                .collect(::choiceTheme)
        }
    }

    private fun choiceTheme(codeStyle: Int) {
        themeForInit = if (codeStyle == MARS_THEME) {
            _themeByClick.tryEmit(R.style.MarsTheme)
            R.style.MarsTheme
        } else {
            _themeByClick.tryEmit(R.style.SpaceTheme)
            R.style.SpaceTheme
        }
    }

    fun getTheme(): Int = themeForInit

    fun onClickSaveTheme(codeStyle: Int) {
        viewModelScope.launch {
            dataStore.edit { it[keyOfTheme] = codeStyle }
            loadThemeFromPref()
        }
    }
}