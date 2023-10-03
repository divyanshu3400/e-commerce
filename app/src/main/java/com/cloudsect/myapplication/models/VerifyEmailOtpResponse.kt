package com.cloudsect.myapplication.models

import com.google.gson.annotations.SerializedName

data class VerifyEmailOtpResponse(
    val message :String,
    val token :String,

    @SerializedName("user_id")
    val userId :Int
)
