package com.cloudsect.myapplication.ui.categories.model

import java.io.Serializable

data class ProductResponse(
    val product_id: Int,
    val images: List<ProductImage>,
    val product_name: String,
    val discount_available: Boolean,
    val discount_price: String,
    val discount_percent: String,
    val product_price: String,
    val new_price: String,
    val meta_title: String,
    val product_description: String,
    val product_available: Boolean,
    val units_in_stock: Int,
    val meta_description: String,
    val created_at: String,
    val category_name: String,
    val brand: Int,
    val category: Int
):Serializable