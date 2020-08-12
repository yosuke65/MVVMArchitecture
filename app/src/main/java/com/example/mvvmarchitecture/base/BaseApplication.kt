package com.example.mvvmarchitecture.base

import android.app.Application
import com.example.mvvmarchitecture.di.AppComponent
import com.example.mvvmarchitecture.di.DaggerAppComponent

class BaseApplication:Application() {
    private lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    fun getAppComponent():AppComponent = appComponent
}