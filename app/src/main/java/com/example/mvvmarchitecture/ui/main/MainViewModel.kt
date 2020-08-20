package com.example.mvvmarchitecture.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmarchitecture.models.RepositoryItem
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    companion object{
        const val TAG = "MainViewModel"
    }

    private val listRepositoryItem: MutableLiveData<List<RepositoryItem>> by lazy {
        MutableLiveData<List<RepositoryItem>>()
    }

    fun getRepoObserver() = listRepositoryItem


    fun onButtonClicked() {
//        mainRepository.getDataFromApi()
        getDataFromApi()
    }

    fun getDataFromApi(){
        val result = viewModelScope.async {
           val list = mainRepository.getDataFromApiSuspend()
            listRepositoryItem.postValue(list)
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.v(TAG, "onCleared(): MainViewModel destroyed")
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