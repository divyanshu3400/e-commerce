package com.cloudsect.myapplication.ui.home


import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BrandModel: BaseObservable() {

    @SerializedName("id")
    @Expose
    var brandId:Int = 0

    @SerializedName("brand_image")
    @Expose
    var brandImage:String = ""

    @SerializedName("brand_name")
    @Expose
    var brandTitle:String = ""


    var status: Boolean = true


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