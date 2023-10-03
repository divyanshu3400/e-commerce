package com.cloudsect.myapplication.retrofit

import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserEmailRequest
import com.cloudsect.myapplication.models.VerifyEmailOtpResponse
import com.cloudsect.myapplication.search.SuggestionEntity
import com.cloudsect.myapplication.ui.categories.model.CategoryResponse
import com.cloudsect.myapplication.ui.categories.model.ChildCatReq
import com.cloudsect.myapplication.ui.categories.model.ChildCatRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("search/suggestions")
    suspend fun getSuggestions(@Query("query") query: String): List<SuggestionEntity>

    //    @POST("send-email-otp/")
//    suspend fun sendOtp(@Header("Authorization") authorization: String, @Body userEmailRequest: com.cloudsect.myapplication.models.UserEmailRequest): Response<MessageResponse>
    @POST("send-email-otp/")
    fun sendOtp(@Body userEmailRequest: UserEmailRequest): Call<MessageResponse>

    @GET("navbar_list/")
    fun getCategoryList(): Call<List<CategoryResponse>>

    @POST("parent-categories-list/")
    fun getChildCategoryList(@Body childCatReq: ChildCatReq): Call<List<ChildCatRes>>

    @POST("verify-email-otp/")
    fun verifyOtp(@Body userEmailRequest: UserEmailRequest): Call<VerifyEmailOtpResponse>

}
