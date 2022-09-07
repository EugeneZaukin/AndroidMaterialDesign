package com.eugene.androidmaterialdesign.ui

import androidx.lifecycle.*
import javax.inject.*

class ViewModelFactory @Inject constructor(
    private val factories: Map<Class<*>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factories.getValue(modelClass as Class<*>).get() as T
    }
}