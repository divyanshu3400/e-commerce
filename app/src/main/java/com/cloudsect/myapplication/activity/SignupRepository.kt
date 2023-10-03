package com.cloudsect.myapplication.activity


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserEmailRequest
import com.cloudsect.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupRepository {
    var messageResponseLiveData: MutableLiveData<MessageResponse> = MutableLiveData<MessageResponse>()

    fun sendOTP(userEmailRequest: UserEmailRequest): MutableLiveData<MessageResponse> {
        val call: Call<MessageResponse> = RetrofitClient.apiService.sendOtp(userEmailRequest)
        call.enqueue(object : Callback<MessageResponse?> {
            override fun onResponse(
                call: Call<MessageResponse?>,
                response: Response<MessageResponse?>
            ) {
                Log.d("TAG", "onFailure: ${response.code()}")
                Log.d("TAG", "onFailure: ${response.body()?.message}")
                if (response.isSuccessful) {
                    messageResponseLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<MessageResponse?>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }
        })
        return messageResponseLiveData
    }
}
