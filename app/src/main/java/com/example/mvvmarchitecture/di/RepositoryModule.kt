package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.api.Endpoint
import com.example.mvvmarchitecture.ui.main.MainRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideMainRepository(endpoint: Endpoint):MainRepository{
        return MainRepository(endpoint)
    }
}