package com.cloudsect.myapplication.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.DataBindingUtil
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.click_listener.GetLoginCredential
import com.cloudsect.myapplication.databinding.ActivityLoginBinding
import com.cloudsect.myapplication.view_model.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), GetLoginCredential {
    private lateinit var binding: ActivityLoginBinding
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(binding.root)

        binding.viewModal = LoginViewModel()
        binding.getCredential = this
        binding.llSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun getLoginCredential(view: View, viewModal: LoginViewModel) {
        Snackbar
            .make(
                view, "Logged in as " + viewModal.email,
                Snackbar.LENGTH_SHORT
            )
            .show()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }, (1000).toLong())
    }
}