package com.eugene.androidmaterialdesign

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.eugene.androidmaterialdesign.di.*

class NasaApp : Application() {
    private lateinit var _appComponent: AppComponent
    val appComponent get() = _appComponent

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is NasaApp -> appComponent
        else -> this.applicationContext.appComponent
    }

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")