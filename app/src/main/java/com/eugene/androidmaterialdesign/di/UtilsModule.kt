package com.eugene.androidmaterialdesign.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.eugene.androidmaterialdesign.dataStore
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {
    @Provides
    fun provideDataStore(context: Context): DataStore<Preferences> = context.dataStore
}