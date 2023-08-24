package com.cloudsect.myapplication.ui.home


import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BrandModel: BaseObservable() {

    var brandId:Int = 0
    var brandImage:String = ""
    var brandTitle:String = ""

    companion object {
        @JvmStatic
        @BindingAdapter("brand_image")
        fun loadImage(view: ImageView, imageUrl: String): String {
            Glide.with(view.context)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
            return imageUrl
        }
    }
}