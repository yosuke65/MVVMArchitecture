package com.example.mvvmarchitecture.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.regex.Pattern

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    companion object{
        const val TAG = "MainViewModel"
    }

    fun getRepoObserver() = mainRepository.listRepositoryItem


    fun onButtonClicked() {
        mainRepository.getDataFromApi()
    }


    override fun onCleared() {
        super.onCleared()
        Log.v("MainViewModel", "onCleared()")
    }

    fun isNumberOdd(i: Int):Boolean {
        return i%2!=0
    }


    fun isEmailAddressValid(email:String):Boolean{
        val regex = "^(.+)@(.+)$"
        val pattern: Pattern = Pattern.compile(regex)
        return email.matches(Regex(regex))
    }
}

/**
 * ViewModel with constructor , without it will crash because view model don't know the param
 */
class MainViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(mainRepository) as T
            throw IllegalArgumentException("It is only create MainViewModel object")

    }

}