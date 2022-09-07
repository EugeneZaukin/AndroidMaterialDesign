package com.eugene.androidmaterialdesign.data.repository

import okhttp3.*
import javax.inject.Inject

class NasaInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}