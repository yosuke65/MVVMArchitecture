package com.example.mvvmarchitecture.di

import com.example.mvvmarchitecture.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class,RepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}