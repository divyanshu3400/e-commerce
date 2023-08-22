package com.cloudsect.myapplication.adapter


import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.cloudsect.myapplication.util.ImageSlider

@BindingAdapter("imageSlider")
fun setImageSlider(viewPager: ViewPager, imageSlider: ImageSlider) {
    viewPager.adapter = imageSlider.createPagerAdapter()
}

