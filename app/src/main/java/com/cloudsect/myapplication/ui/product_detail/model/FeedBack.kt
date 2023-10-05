package com.cloudsect.myapplication.ui.product_detail.model

import java.io.Serializable

data class FeedBack(
    val id: Int,
    val rating: Int,
    val comment: String,
    val created_at: String,
    val user_name: String,
    val product_name: String
): Serializable