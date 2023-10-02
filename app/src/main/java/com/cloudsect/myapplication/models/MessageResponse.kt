package com.cloudsect.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MessageResponse(
    val message:String,
    val token: String,
    @SerializedName("user_id")
    @Expose
    val userId:Int
)
