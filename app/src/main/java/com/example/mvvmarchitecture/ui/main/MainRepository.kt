package com.example.mvvmarchitecture.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitecture.api.Endpoint
import com.example.mvvmarchitecture.models.RepositoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class MainRepository(private val endpoint: Endpoint) {

    companion object {
        private const val TAG = "MainRepository"
    }


    suspend fun getDataFromApiSuspend(): List<RepositoryItem>? {
        return withContext(Dispatchers.IO) {
            val call = endpoint.getRepositoryURL()
            val response = call.execute()
            if (response.code() in 200..399) {
//            val response = call.awaitResponse()
//            Log.d(TAG, "Get the response: ${response}")
//            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}