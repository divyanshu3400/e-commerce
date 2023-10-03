package com.cloudsect.myapplication.ui.categories.model

data class CategoryResponse(
    val nav_id: Int,
    val nav_name: String,
    val nav_logo: String,
    val nav_status: Boolean,
    val created_at: String
)
