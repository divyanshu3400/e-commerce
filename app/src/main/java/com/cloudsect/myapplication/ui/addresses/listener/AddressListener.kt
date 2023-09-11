package com.cloudsect.myapplication.ui.addresses.listener

import com.cloudsect.myapplication.ui.addresses.model.AddressModel

interface AddressListener {
    fun deleteAddress(modal:AddressModel)
    fun updateAddress(modal:AddressModel)
}