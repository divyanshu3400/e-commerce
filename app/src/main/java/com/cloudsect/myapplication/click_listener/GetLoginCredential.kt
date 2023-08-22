package com.cloudsect.myapplication.click_listener

import android.view.View
import com.cloudsect.myapplication.models.LoginUserModal
import com.cloudsect.myapplication.view_model.LoginViewModel

interface GetLoginCredential {
    fun getLoginCredential(view: View,viewModal: LoginViewModel)
}