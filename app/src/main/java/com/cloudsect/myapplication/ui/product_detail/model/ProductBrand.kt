package com.cloudsect.myapplication.ui.product_detail.model

import java.io.Serializable

data class ProductBrand(
    val id: Int,
    val brand_name: String,
    val brand_image: String,
    val status: Boolean
): Serializable
