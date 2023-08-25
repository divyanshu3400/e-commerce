package com.cloudsect.myapplication.ui.myorders

import androidx.lifecycle.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyOrdersViewModel : ViewModel() {
    private val _backgroundColorHex = MutableLiveData<String>()
    val backgroundColorHex: LiveData<String>
        get() = _backgroundColorHex

    fun setBackgroundColorHex(hexValue: String) {
        _backgroundColorHex.value = hexValue
    }
}
