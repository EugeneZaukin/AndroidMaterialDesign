package com.eugene.androidmaterialdesign.di

import androidx.lifecycle.ViewModel
import com.eugene.androidmaterialdesign.ui.main.MainViewModel
import com.eugene.androidmaterialdesign.ui.main.viewpager.DayFragmentViewModel
import com.eugene.androidmaterialdesign.ui.settings.SettingsViewModel
import dagger.*
import dagger.multibindings.*

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ClassKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(DayFragmentViewModel::class)
    fun provideDayFragmentViewModel(dayFragmentViewModel: DayFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(SettingsViewModel::class)
    fun provideSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}