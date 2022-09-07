package com.eugene.androidmaterialdesign.di

import com.eugene.androidmaterialdesign.data.repository.*
import dagger.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun OkHttpClient(interceptor: NasaInterceptor, httpLogging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLogging)
            .build()

    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://api.nasa.gov/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    fun api(retrofit: Retrofit): NasaAPI = retrofit.create(NasaAPI::class.java)

    @Provides
    fun provideNetworkRepos(api: NasaAPI): NetworkRepository = NetworkRepositoryImpl(api)
}