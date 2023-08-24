package com.cloudsect.myapplication.ui.wishlist.model

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cloudsect.myapplication.ui.product_detail.adapter.SizeAdapter
import java.io.Serializable

class WishlistProductModel :BaseObservable() , Serializable{

    @get:Bindable
    var productTitle:String = ""
    @get:Bindable
    var productSecTitle:String = ""

    @get:Bindable
    var productDesc:String = ""

    @get:Bindable
    var productPrice:String = ""

    @get:Bindable
    var productSizes: List<String>? = null

    @get:Bindable
    var colors: List<String>? = null

    @get:Bindable
    var rating:Float =0.0F

    @get:Bindable
    var desc:String = ""

    @get:Bindable
    var totalReviews:Int = 0

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