package com.eugene.androidmaterialdesign.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.intPreferencesKey
import com.eugene.androidmaterialdesign.*
import com.eugene.androidmaterialdesign.ui.settings.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Установка темы
        setTheme(getAppTheme())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    //Тема из DataStorePreferences
    private fun getAppTheme(): Int {
        var theme: Int

        runBlocking {
            theme = dataStore.data.first()[intPreferencesKey(APP_THEME)] ?: SPACE_THEME
        }
        return if (theme == MARS_THEME) R.style.MarsTheme else R.style.SpaceTheme
    }
}