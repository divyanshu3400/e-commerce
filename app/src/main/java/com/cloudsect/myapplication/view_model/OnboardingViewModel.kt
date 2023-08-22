package com.cloudsect.myapplication.view_model

import android.content.Context
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.cloudsect.myapplication.util.ImageSlider


class OnboardingViewModel(context: Context, viewPager: ViewPager,dotsLayout: LinearLayout) : ViewModel() {
    val imageSlider: ImageSlider = ImageSlider(context, viewPager, dotsLayout)

    fun setImageUrls(imageUrls: Array<Int>) {
        imageSlider.setImageUrls(imageUrls)
    }

}


class OnboardingViewModelFactory(
    private val context: Context,
    private val viewPager: ViewPager,
    private val dotsLayout: LinearLayout
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OnboardingViewModel(context, viewPager, dotsLayout) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
