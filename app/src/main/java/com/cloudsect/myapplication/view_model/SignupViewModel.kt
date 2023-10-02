package com.cloudsect.myapplication.view_model

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.cloudsect.myapplication.models.UserEmailRequest


class SignupViewModel : BaseObservable() {

    private var userEmailRequest: UserEmailRequest = UserEmailRequest()

    // toast messages
    private val successMessage = "Login success"
    private val errorMessage = "Entered Email-ID or Password is not valid"

    // Creates getter and setter methods
    // for toast message
    @Bindable // Creates string variable for
    // toast message
    var toastMessage: String? = null
        private set

    private fun setToastMessage(toastMessage: String) {
        this.toastMessage = toastMessage
    }

    @get:Bindable
    var userEmail: String
        // Creates getter and setter methods
        get() = userEmailRequest.email
        set(email) {
            userEmailRequest.email=email
            notifyChange()
        }

    fun onButtonClicked() {
        if (isValid) {
            setToastMessage(successMessage)
            Log.d("TAG", "User Email: $userEmail")
        } else setToastMessage(errorMessage)
    }

    private val isValid: Boolean
        get() = (!TextUtils.isEmpty(userEmail) && userEmail.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() })
}