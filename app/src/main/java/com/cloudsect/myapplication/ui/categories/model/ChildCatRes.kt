package com.cloudsect.myapplication.ui.categories.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


data class ChildCatRes(
    val pcat_id: Int,
    val pcat_name: String,
    val pcat_logos: String,
    val pcat_status: Boolean,
    val created_at: String,
    val nav: Int
)
{
    companion object {
        @JvmStatic
        @BindingAdapter("child_cat_image")
        fun loadImage(view: ImageView, imageUrl: String): String {
            Glide.with(view.context)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
            return imageUrl
        }
    }
}
