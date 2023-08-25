package com.cloudsect.myapplication.ui.myorders.model

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cloudsect.myapplication.R
import java.io.Serializable

class MyOrderModal: BaseObservable(), Serializable {

    @get:Bindable
    var productTitle:String = ""

    @get:Bindable
    var productSecTitle:String = ""

    @get:Bindable
    var productDesc:String = ""

    @get:Bindable
    var productPrice:String = ""

    @get:Bindable
    var productSizes: String? = null

    @get:Bindable
    var colorsHex: String? = null

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
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context).load(imageUrl).placeholder(R.drawable.camera_image).into(view)
        }

        @JvmStatic
        @BindingAdapter("ll_visibility")
        fun setProductSizesVisibility(view: View, productSizes: String?) {
            view.visibility = if (!productSizes.isNullOrEmpty()) View.VISIBLE else View.GONE
        }
    }
}
