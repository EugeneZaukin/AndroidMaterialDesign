package com.eugene.androidmaterialdesign.di

import android.content.Context
import dagger.*

@Component(modules = [NetworkModule::class])
interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}