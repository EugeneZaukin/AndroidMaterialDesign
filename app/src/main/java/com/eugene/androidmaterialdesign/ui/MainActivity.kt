package com.eugene.androidmaterialdesign.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.intPreferencesKey
import com.eugene.androidmaterialdesign.*
import com.eugene.androidmaterialdesign.ui.main.MainFragment
import com.eugene.androidmaterialdesign.ui.settings.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Установка темы
        getAppTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commitNow()
        }
    }

    //Тема из DataStorePreferences
    private fun getAppTheme() {
        var theme: Int

        runBlocking {
            theme = dataStore.data.first()[intPreferencesKey(APP_THEME)] ?: SPACE_THEME
        }

        if (theme == MARS_THEME)
            setTheme(R.style.MarsTheme)
        else
            setTheme(R.style.SpaceTheme)
    }
}