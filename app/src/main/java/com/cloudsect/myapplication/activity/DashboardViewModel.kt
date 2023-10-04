package com.cloudsect.myapplication.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cloudsect.myapplication.models.UserDataResponse
import com.cloudsect.myapplication.models.UserIdModel

class DashboardViewModel(application: Application):AndroidViewModel(application) {
    private val dashboardRepository = DashboardRepository()
    fun getUserData(token:String,userIdModel: UserIdModel): LiveData<UserDataResponse> {
        return dashboardRepository.getUserDatas(token,userIdModel)
    }

}