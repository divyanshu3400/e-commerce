package com.cloudsect.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserEmailRequest
import com.cloudsect.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupRepository {
    private val apiService = RetrofitClient.apiService

    val mutableLiveData = MutableLiveData<MessageResponse>()

    suspend fun requestOtp(userEmailRequest: UserEmailRequest):MutableLiveData<MessageResponse>{

        val call: Call<MessageResponse> = apiService.sendOtp(userEmailRequest)
        call.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                } else {
                    Log.d("TAG", "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                // Handle network or other errors
            }
        })

        return mutableLiveData
    }
}
