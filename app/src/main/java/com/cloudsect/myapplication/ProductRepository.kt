package com.cloudsect.myapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.WishlistReq
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.ui.product_detail.model.ProductDetailReq
import com.cloudsect.myapplication.ui.product_detail.model.ProductDetailResponse
import com.cloudsect.myapplication.util.Constants
import com.cloudsect.myapplication.util.SharedPreferencesManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ProductRepository {

    val messageResponseLiveData:MutableLiveData<MessageResponse> = MutableLiveData<MessageResponse>()
    val productDetailResponseLiveData:MutableLiveData<ProductDetailResponse> = MutableLiveData<ProductDetailResponse>()
    fun removeFromWishlist(context: Context,productId: Int):MutableLiveData<MessageResponse> {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val token = "Token ${sharedPreferencesManager.getString(Constants.TOKEN)}"
        val call :Call<MessageResponse> = RetrofitClient.apiService
            .removeFromWishlist(token,WishlistReq(sharedPreferencesManager.getInt(Constants.USER_ID),productId))
        call.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>
            ) {
                Log.d("TAG", "removeFromWishlist: ${response.code()}")
                if (response.isSuccessful) {
                    messageResponseLiveData.value = response.body()
                    Log.d("TAG", "removeFromWishlist: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Log.d("TAG", "removeFromWishlist: $t")
            }
        })
        return messageResponseLiveData
    }

    fun addToWishList(context: Context,productId: Int):MutableLiveData<MessageResponse> {
        val sharedPreferencesManager = SharedPreferencesManager(context)
        val token = "Token ${sharedPreferencesManager.getString(Constants.TOKEN)}"
        val call :Call<MessageResponse> = RetrofitClient.apiService
            .addToWishlist(token,WishlistReq(sharedPreferencesManager.getInt(Constants.USER_ID),productId))
        call.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>
            ) {
                Log.d("TAG", "addToWishList: ${response.code()}")
                if (response.isSuccessful) {
                    messageResponseLiveData.value = response.body()

                    Log.d("TAG", "addToWishList: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Log.d("TAG", "addToWishList: $t")
            }
        })
        return  messageResponseLiveData
    }
    fun productDetail(productDetailReq: ProductDetailReq):MutableLiveData<ProductDetailResponse> {
        val call :Call<ProductDetailResponse> = RetrofitClient.apiService
            .productDetail(productDetailReq)
        call.enqueue(object : Callback<ProductDetailResponse> {
            override fun onResponse(call: Call<ProductDetailResponse>, response: Response<ProductDetailResponse>
            ) {
                Log.d("TAG", "productDetail: ${response.code()}")
                if (response.isSuccessful) {
                    productDetailResponseLiveData.value = response.body()

                    Log.d("TAG", "productDetail: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ProductDetailResponse>, t: Throwable) {
                Log.d("TAG", "productDetail: $t")
            }
        })
        return  productDetailResponseLiveData
    }


}