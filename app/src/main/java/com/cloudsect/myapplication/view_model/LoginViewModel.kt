package com.cloudsect.myapplication.view_model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.cloudsect.myapplication.BR


class LoginViewModel : BaseObservable(){

    @get:Bindable
    var email : String = "divyanshurock39@gmail.com"
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var password : String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

}
