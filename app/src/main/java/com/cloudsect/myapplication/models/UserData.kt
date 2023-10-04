package com.cloudsect.myapplication.models


data class UserData(
    val user_id: Int,
    val is_superuser: Boolean,
    val username: String,
    val name: String?,
    val email: String,
    val is_staff: Boolean,
    val is_active: Boolean,
    val date_joined: String,
    val phone_number: String?,
    val age: Int,
    val gender: String,
    val profile_image: String
)
