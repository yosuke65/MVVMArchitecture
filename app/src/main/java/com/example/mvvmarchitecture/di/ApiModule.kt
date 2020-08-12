package com.example.mvvmarchitecture.di

import androidx.annotation.RestrictTo
import com.example.mvvmarchitecture.api.ApiClient
import com.example.mvvmarchitecture.api.Endpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofitClient():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideEndPoint(retrofit: Retrofit):Endpoint{
        return retrofit.create(Endpoint::class.java)
    }
}