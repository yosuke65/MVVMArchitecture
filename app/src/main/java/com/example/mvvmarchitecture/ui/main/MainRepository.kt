package com.example.mvvmarchitecture.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitecture.api.Endpoint
import com.example.mvvmarchitecture.models.RepositoryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainRepository(private val endpoint: Endpoint){

    val listRepositoryItem: MutableLiveData<List<RepositoryItem>> by lazy{
        MutableLiveData<List<RepositoryItem>>()
    }
    open fun getDataFromApi(){
        endpoint.getRepositoryURL().enqueue(object: Callback<List<RepositoryItem>>{
            override fun onFailure(call: Call<List<RepositoryItem>>, t: Throwable) {
                //
            }

            override fun onResponse(
                call: Call<List<RepositoryItem>>,
                response: Response<List<RepositoryItem>>
            ) {
                val list = response.body()
                listRepositoryItem.postValue(list)
            }

        })
    }

    open fun getWelcomeMsg(): String {
        return "getWelcomeMsg"
    }
}