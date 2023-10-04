package com.cloudsect.myapplication.retrofit

import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserDataResponse
import com.cloudsect.myapplication.models.UserEmailRequest
import com.cloudsect.myapplication.models.UserIdModel
import com.cloudsect.myapplication.models.VerifyEmailOtpResponse
import com.cloudsect.myapplication.search.SuggestionEntity
import com.cloudsect.myapplication.ui.categories.model.CategoryResponse
import com.cloudsect.myapplication.ui.categories.model.ChildCatReq
import com.cloudsect.myapplication.ui.categories.model.ChildCatRes
import com.cloudsect.myapplication.ui.categories.model.ParentProductReq
import com.cloudsect.myapplication.ui.categories.model.ProductResponse
import com.cloudsect.myapplication.ui.home.BrandModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/suggestions")
    suspend fun getSuggestions(@Query("query") query: String): List<SuggestionEntity>

    @POST("send-email-otp/")
    fun sendOtp(@Body userEmailRequest: UserEmailRequest): Call<MessageResponse>

    @GET("navbar_list/")
    fun getCategoryList(): Call<List<CategoryResponse>>
    @GET("brand-list/")
    fun getBrands(): Call<List<BrandModel>>

    @GET("pcat-products/{pcat_id}")
    fun getParentProducts(@Path("pcat_id") id: Int): Call<List<ProductResponse>>

    @POST("parent-categories-list/")
    fun getChildCategoryList(@Body childCatReq: ChildCatReq): Call<List<ChildCatRes>>

    @POST("verify-email-otp/")
    fun verifyOtp(@Body userEmailRequest: UserEmailRequest): Call<VerifyEmailOtpResponse>

    @POST("user-profile/")
    fun getUserData(@Header("Authorization") token: String, @Body userIdModel: UserIdModel): Call<UserDataResponse>
    @POST("logout/")
    fun logout(@Header("Authorization") token: String, @Body userIdModel: UserIdModel): Call<MessageResponse>

}
