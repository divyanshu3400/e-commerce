package com.cloudsect.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ActivityViewImageBinding
import com.cloudsect.myapplication.ui.product_detail.adapter.ProductImageAdapter
import com.cloudsect.myapplication.util.ImageSliderString

class ViewImageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_image)

        setContentView(binding.root)
        setImageSlider()
    }
    private fun setImageSlider() {
        val imageSlider =
            applicationContext.let { ProductImageAdapter(it, binding.viewPager, binding.dotsLayout,false) }
        val imageUrls = intent.getStringArrayExtra("images")
        if (imageUrls!=null)
            imageSlider.setImageUrls(imageUrls)
    }

}