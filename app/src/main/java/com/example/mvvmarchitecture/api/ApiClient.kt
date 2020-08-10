package com.example.mvvmarchitecture.api

import com.example.mvvmarchitecture.models.RepositoryItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object ApiClient {
    private const val BASE_URL = "https://ghapi.huchen.dev/"

    private val _endpoint: Endpoint by lazy {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        client.create(Endpoint::class.java)
    }

    fun getApiEndpoint(): Endpoint = _endpoint
}

interface Endpoint {
    
//    @GET("repositories?language={language}&since={since}&spoken_language_code={spoken_language_code}")
//    fun getRepositoryURL(
//        @Query("language") lang: String = "",
//        @Query("since") since: String = "",
//        @Query("spoken_language_code") slc: String = ""
//    ): Call<List<RepositoryItem>>
//
    @GET("repositories?language=&since=&spoken_language_code=")
    fun getRepositoryURL(): Call<List<RepositoryItem>>
}