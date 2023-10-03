package com.cloudsect.myapplication.ui.categories.model


data class ChildCatRes(
    val pcat_id: Int,
    val pcat_name: String,
    val pcat_logos: String,
    val pcat_status: Boolean,
    val created_at: String,
    val nav: Int
)
