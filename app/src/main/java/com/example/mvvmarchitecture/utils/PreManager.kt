package com.example.mvvmarchitecture.utils

import android.content.Context
import android.content.SharedPreferences

object PreManager {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences("myApp", Context.MODE_PRIVATE)
    }

    fun savePreferenceManager(key:String){
//        sharedPreferences.edit().putString()
    }
}