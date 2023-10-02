package com.cloudsect.myapplication.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserEmailRequest

class SignupViewModel(application: Application) : AndroidViewModel(application) {
    private var signupRepository: SignupRepository = SignupRepository()

    fun sendEmailOtp(userEmailRequest: UserEmailRequest): LiveData<MessageResponse> {
        return signupRepository.sendOTP(userEmailRequest)
    }
}