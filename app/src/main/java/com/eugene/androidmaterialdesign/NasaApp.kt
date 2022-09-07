package com.eugene.androidmaterialdesign

import android.app.Application
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