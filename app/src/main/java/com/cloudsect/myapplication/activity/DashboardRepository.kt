package com.cloudsect.myapplication.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudsect.myapplication.models.UserDataResponse
import com.cloudsect.myapplication.models.UserIdModel
import com.cloudsect.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardRepository {

    var userDataResponseLiveData: MutableLiveData<UserDataResponse> = MutableLiveData<UserDataResponse>()

    fun getUserDatas(token:String,userIdModel: UserIdModel): MutableLiveData<UserDataResponse> {
        val call: Call<UserDataResponse> = RetrofitClient.apiService.getUserData(token,userIdModel)
        call.enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
                Log.d("TAG", "getUserDatas: ${response.code()}")

                if (response.isSuccessful) {
                    userDataResponseLiveData.value = response.body()
                    Log.d("TAG", "getUserDatas: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                Log.d("TAG", "getUserDatas: $t")

            }
        })
        return userDataResponseLiveData
    }
}