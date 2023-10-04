package com.cloudsect.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudsect.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    var brandModelLiveData: MutableLiveData<List<BrandModel>> = MutableLiveData<List<BrandModel>>()

    fun getBrand(): MutableLiveData<List<BrandModel>> {
        val call: Call<List<BrandModel>> = RetrofitClient.apiService.getBrands()
        call.enqueue(object : Callback<List<BrandModel>?> {
            override fun onResponse(
                call: Call<List<BrandModel>?>,
                response: Response<List<BrandModel>?>
            ) {
                Log.d("TAG", "getNavItemList: ${response.code()}")

                if (response.isSuccessful) {
                    brandModelLiveData.value = response.body()
                    Log.d("TAG", "getNavItemList: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<BrandModel>?>, t: Throwable) {
                Log.d("TAG", "getNavItemList: $t")

            }
        })
        return brandModelLiveData
    }

}