package com.cloudsect.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.click_listener.ClickListeners
import com.cloudsect.myapplication.databinding.ActivityOnboardingBinding
import com.cloudsect.myapplication.view_model.OnboardingViewModel
import com.cloudsect.myapplication.view_model.OnboardingViewModelFactory

class OnboardingActivity : AppCompatActivity(), ClickListeners {
    private lateinit var binding : ActivityOnboardingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        setContentView(binding.root)
        setImageSlider()
    }

    private fun setImageSlider() {
        val viewModelFactory = OnboardingViewModelFactory(this, binding.viewPager, binding.dotsLayout)
        val viewModel = ViewModelProvider(this, viewModelFactory)[OnboardingViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.onButtonClick = this
        val imageUrls = arrayOf(R.drawable.image1, R.drawable.image2, R.drawable.image3
        ,R.drawable.vc,R.drawable.disc)
        viewModel.setImageUrls(imageUrls)
    }

    override fun onNextButtonClicked(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }
}