package com.cloudsect.myapplication.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.click_listener.GetLoginCredential
import com.cloudsect.myapplication.databinding.ActivitySignupBinding
import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserEmailRequest
import com.cloudsect.myapplication.models.VerifyEmailOtpResponse
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.util.Constants
import com.cloudsect.myapplication.util.EmailValidator
import com.cloudsect.myapplication.util.SharedPreferencesManager
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity(), GetLoginCredential {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel
    lateinit var email:String
    private val context : Context = this
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        binding.executePendingBindings()
        binding.getCredential = this

        sharedPreferencesManager = SharedPreferencesManager(context)
    }

    override fun getLoginCredential(view: View) {
        email = binding.emailEt.text.toString().trim()
        val emailValidator = EmailValidator()
        val userEmailRequest = UserEmailRequest()
        if (binding.emailEt.text.toString().isNotEmpty() &&
            emailValidator.isEmailValid(binding.emailEt.text.toString()))
        {
            userEmailRequest.email = email
            binding.loader.visibility = View.VISIBLE
            sendOTP(view,userEmailRequest)
        }
        else{
            Snackbar.make(view, "Enter Valid email", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun verifyCredential(view: View) {
        val userEmailRequest = UserEmailRequest()
        userEmailRequest.email = email
        userEmailRequest.otp = binding.otpEt.text.toString().trim()
        binding.loader.visibility = View.VISIBLE
        if (binding.otpEt.text.toString().trim().isNotEmpty()){
            verifyOtp(view,userEmailRequest)
        }

    }

    private fun sendOTP(view: View,userEmailRequest: UserEmailRequest) {

        val call: Call<MessageResponse> = RetrofitClient.apiService.sendOtp(userEmailRequest)
        call.enqueue(object : Callback<MessageResponse?> {
            override fun onResponse(
                call: Call<MessageResponse?>,
                response: Response<MessageResponse?>
            ) {
                binding.loader.visibility = View.GONE
                if (response.isSuccessful) {
                    Snackbar.make(view, "${response.body()?.message}", Snackbar.LENGTH_SHORT).show()
                    startCountdownTimer()
                    binding.otpTextInp.visibility = View.VISIBLE
                    binding.verifyOtp.visibility = View.VISIBLE
                    binding.requestOtp.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MessageResponse?>, t: Throwable) {
                binding.loader.visibility = View.GONE
            }
        })
    }

    private fun verifyOtp(view: View, userEmailRequest: UserEmailRequest){
        val call: Call<VerifyEmailOtpResponse> = RetrofitClient.apiService.verifyOtp(userEmailRequest)
        call.enqueue(object : Callback<VerifyEmailOtpResponse?> {
            override fun onResponse(
                call: Call<VerifyEmailOtpResponse?>,
                response: Response<VerifyEmailOtpResponse?>
            ) {
                binding.loader.visibility = View.GONE
                if (response.isSuccessful && response.body() !=null) {
                    Snackbar.make(view, "${response.body()?.message}", Snackbar.LENGTH_SHORT).show()
                    sharedPreferencesManager.saveBool(Constants.IS_LOGGED_IN,true)
                    sharedPreferencesManager.saveString(Constants.TOKEN, response.body()!!.token)
                    sharedPreferencesManager.saveInt(Constants.USER_ID, response.body()!!.userId)
                    startActivity(Intent(context, DashboardActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<VerifyEmailOtpResponse?>, t: Throwable) {
                binding.loader.visibility = View.GONE
            }
        })
    }

    private fun startCountdownTimer() {
        val countdownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.llTimer.visibility = View.VISIBLE
                val secondsRemaining = millisUntilFinished / 1000
                binding.timer.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                binding.llTimer.visibility = View.GONE

            }
        }

        countdownTimer.start()
    }
}