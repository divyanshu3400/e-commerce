package com.cloudsect.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("user_data")
    @Expose
    val userData:UserData
)
