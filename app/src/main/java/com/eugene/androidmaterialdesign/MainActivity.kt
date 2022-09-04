package com.eugene.androidmaterialdesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eugene.androidmaterialdesign.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private val NAME_SHARED_PREFERENCE = "LOGIN"
    private val APP_THEME = "APP_THEME"
    private val MARS_THEME = 0

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

    //Тема из SharedPreferences
    private fun getAppTheme() {
        val sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE)
        if (sharedPreferences != null) {
            val codeStyle = sharedPreferences.getInt(APP_THEME, 0)
            if (codeStyle == MARS_THEME) {
                setTheme(R.style.MarsTheme)
            } else {
                setTheme(R.style.SpaceTheme)
            }
        }
    }
}