package com.cloudsect.myapplication.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.util.Constants
import com.cloudsect.myapplication.util.SharedPreferencesManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sharedPreferencesManager  = SharedPreferencesManager(applicationContext)
        Log.d("TAG", "onCreate: ${sharedPreferencesManager.getBool(Constants.IS_LOGGED_IN)}")
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPreferencesManager.getBool(Constants.IS_LOGGED_IN))
            {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }

        }, (2 * 1000).toLong()) // wait for 2 seconds

    }
}