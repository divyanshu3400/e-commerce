package com.cloudsect.myapplication.ui.addresses.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class AddressModel:BaseObservable() {

    @get:Bindable
    var addressId:Int=0

    @get:Bindable
    var addressType:String =""

    @get:Bindable
    var subLocality:String=""

    @get:Bindable
    var locality:String =""

    @get:Bindable
    var pinCode:String=""

    @get:Bindable
    var city:String=""

    @get:Bindable
    var state:String=""

    @get:Bindable
    var country:String =""

    @get:Bindable
    var phoneNumber:String=""

    @get:Bindable
    var combinedAddress:String=""
}