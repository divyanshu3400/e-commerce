package com.cloudsect.myapplication.ui.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.ui.categories.model.CategoryResponse
import com.cloudsect.myapplication.ui.categories.model.ChildCatReq
import com.cloudsect.myapplication.ui.categories.model.ChildCatRes
import com.cloudsect.myapplication.ui.categories.model.ParentProductReq
import com.cloudsect.myapplication.ui.categories.model.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository {

    var categoryResponseLiveData: MutableLiveData<List<CategoryResponse>> = MutableLiveData<List<CategoryResponse>>()
    var childCategoryResponseLiveData: MutableLiveData<List<ChildCatRes>> = MutableLiveData<List<ChildCatRes>>()
    var productResponseLiveData: MutableLiveData<List<ProductResponse>> = MutableLiveData<List<ProductResponse>>()

    fun getNavItemList(): MutableLiveData<List<CategoryResponse>> {
        val call: Call<List<CategoryResponse>> = RetrofitClient.apiService.getCategoryList()
        call.enqueue(object : Callback<List<CategoryResponse>?> {
            override fun onResponse(
                call: Call<List<CategoryResponse>?>,
                response: Response<List<CategoryResponse>?>
            ) {
                Log.d("TAG", "getNavItemList: ${response.code()}")

                if (response.isSuccessful) {
                    categoryResponseLiveData.value = response.body()
                    Log.d("TAG", "getNavItemList: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<CategoryResponse>?>, t: Throwable) {
                Log.d("TAG", "getNavItemList: $t")

            }
        })
        return categoryResponseLiveData
    }
    fun getNavChildItemList(childCatReq: ChildCatReq): MutableLiveData<List<ChildCatRes>> {
        val call: Call<List<ChildCatRes>> = RetrofitClient.apiService.getChildCategoryList(childCatReq)
        call.enqueue(object : Callback<List<ChildCatRes>?> {
            override fun onResponse(
                call: Call<List<ChildCatRes>?>,
                response: Response<List<ChildCatRes>?>
            ) {
                if (response.isSuccessful) {
                    childCategoryResponseLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<ChildCatRes>?>, t: Throwable) {
            }
        })
        return childCategoryResponseLiveData
    }
    fun getParentProduct(parentProductReq: Int): MutableLiveData<List<ProductResponse>> {
        val call: Call<List<ProductResponse>> = RetrofitClient.apiService.getParentProducts(parentProductReq)
        call.enqueue(object : Callback<List<ProductResponse>?> {
            override fun onResponse(
                call: Call<List<ProductResponse>?>,
                response: Response<List<ProductResponse>?>
            ) {
                Log.d("TAG", "onResponse: ${response.code()}")
                if (response.isSuccessful) {
                    productResponseLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<ProductResponse>?>, t: Throwable) {
                Log.d("TAG", "onResponse: $t")

            }
        })
        return productResponseLiveData
    }

}