package com.eugene.androidmaterialdesign.di

import android.content.Context
import com.eugene.androidmaterialdesign.ui.ViewModelFactory
import dagger.*

@Component(modules = [NetworkModule::class, ViewModelModule::class, UtilsModule::class])
interface AppComponent {
    val viewModelFactory: ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}