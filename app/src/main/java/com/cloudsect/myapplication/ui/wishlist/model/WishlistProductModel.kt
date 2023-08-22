package com.cloudsect.myapplication.ui.wishlist.model

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class WishlistProductModel :BaseObservable(){

    @get:Bindable
    var productTitle:String = ""

    @get:Bindable
    var productDesc:String = ""

    @get:Bindable
    var productPrice:String = ""

    var productImageUrl:String = ""



    companion object {
        @JvmStatic
        @BindingAdapter("product_image")
        fun loadImage(view: ImageView, imageUrl: String): String {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
            return imageUrl
        }
    }
}