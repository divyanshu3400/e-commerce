package com.cloudsect.myapplication.ui.profile

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserProfileModel: BaseObservable() {

    @get:Bindable
    var userEmail:String = ""

    @get:Bindable
    var userName:String = ""

    var userProfileImage:String = ""

    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String): String {
            Glide.with(view.context)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
            return imageUrl
        }
    }
}