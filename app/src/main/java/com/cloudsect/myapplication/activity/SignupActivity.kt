package com.cloudsect.myapplication.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.click_listener.GetLoginCredential
import com.cloudsect.myapplication.databinding.ActivitySignupBinding
import com.cloudsect.myapplication.models.UserEmailRequest
import com.cloudsect.myapplication.util.EmailValidator
import com.google.android.material.snackbar.Snackbar


class SignupActivity : AppCompatActivity(), GetLoginCredential {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        binding.executePendingBindings()
        binding.getCredential = this

    }

    override fun getLoginCredential(view: View) {
        val emailValidator = EmailValidator()
        val userEmailRequest = UserEmailRequest()
        if (binding.emailEt.text.toString().isNotEmpty() &&
            emailValidator.isEmailValid(binding.emailEt.text.toString()))
        {
            userEmailRequest.email = binding.emailEt.text.toString().trim()
            viewModel.sendEmailOtp(userEmailRequest)
                .observe(this) { messageResponse ->
                    Snackbar.make(view, messageResponse.message, Snackbar.LENGTH_SHORT).show()
                }
        }
        else{
            Snackbar.make(view, "Enter Valid email", Snackbar.LENGTH_SHORT).show()
        }
    }


}