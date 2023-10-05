package com.cloudsect.myapplication.ui.product_detail.model

import com.google.gson.annotations.SerializedName

data class ProductDetailReq(
    @SerializedName("product_id")
    val productId : Int,

    @SerializedName("category_name")
    val categoryName : String
)