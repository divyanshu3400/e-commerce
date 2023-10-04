package com.cloudsect.myapplication.ui.categories.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

data class CategoryResponse(
    val nav_id: Int,
    val nav_name: String,
    val nav_logo: String,
    val nav_status: Boolean,
    val created_at: String
)

{
    companion object {
        @JvmStatic
        @BindingAdapter("cat_logo")
        fun loadImage(view: ImageView, imageUrl: String): String {
            Glide.with(view.context)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
            return imageUrl
        }
    }
}
