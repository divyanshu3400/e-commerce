package com.cloudsect.myapplication.ui.product_detail.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class ColorModel: BaseObservable() {

    @get:Bindable
    var colorId:Int=0

    @get:Bindable
    var colorHex:String =""

    @get:Bindable
    var colorName:String=""
}