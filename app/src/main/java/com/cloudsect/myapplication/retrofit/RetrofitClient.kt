package com.cloudsect.myapplication.retrofit

import com.cloudsect.myapplication.search.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://your.api.base.url/" // Replace with your API base URL

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun create(apiService: Class<ApiService>): ApiService {
        return retrofit.create(apiService)
    }
}
