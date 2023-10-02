package com.cloudsect.myapplication.search

import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserEmailRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("search/suggestions")
    suspend fun getSuggestions(@Query("query") query: String): List<SuggestionEntity>

    //    @POST("send-email-otp/")
//    suspend fun sendOtp(@Header("Authorization") authorization: String, @Body userEmailRequest: com.cloudsect.myapplication.models.UserEmailRequest): Response<MessageResponse>
    @POST("send-email-otp/")
    fun sendOtp(@Body userEmailRequest: UserEmailRequest): Call<MessageResponse>

}
