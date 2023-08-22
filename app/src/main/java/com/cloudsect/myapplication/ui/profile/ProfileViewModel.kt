package com.cloudsect.myapplication.ui.profile

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ProfileViewModel : ViewModel() {

    val userProfileModel = MutableLiveData<UserProfileModel>()

    private val itemImage = MutableLiveData<Drawable>()

    fun getItemImage(): LiveData<Drawable> {
        return itemImage
    }

    // Setter for itemImage
    fun setItemImage(image: Drawable) {
        itemImage.value = image
    }
}