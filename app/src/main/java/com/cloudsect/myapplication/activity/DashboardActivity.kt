package com.cloudsect.myapplication.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ActivityDashboardBinding
import com.cloudsect.myapplication.models.MessageResponse
import com.cloudsect.myapplication.models.UserIdModel
import com.cloudsect.myapplication.retrofit.RetrofitClient
import com.cloudsect.myapplication.util.Constants
import com.cloudsect.myapplication.util.SharedPreferencesManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: DashboardViewModel
    private val context :Context =this
    private lateinit var sharedPreferencesManager : SharedPreferencesManager
    private lateinit var token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        sharedPreferencesManager = SharedPreferencesManager(context)
        token = "Token ${sharedPreferencesManager.getString(Constants.TOKEN)}"

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        val navView: BottomNavigationView = binding.appBarMain.contentMain.bottomNavView
        navController = findNavController(R.id.nav_host_fragment_activity_dashboard)

        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home,R.id.navigation_category,
                R.id.navigation_profile,R.id.navigation_cart),
            binding.drawerLayout
        )

        setBadges(navView)

        navView.setupWithNavController(navController)
//            setupActionBarWithNavController(navController,binding.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        loadProfile()
        binding.btnLogout?.setOnClickListener {
            val sharedPreferencesManager = SharedPreferencesManager(context)
            val userIdModel = UserIdModel(sharedPreferencesManager.getInt(Constants.USER_ID))

            logout(token,userIdModel) }
    }

    private fun loadProfile() {
        val userIdModel = UserIdModel(sharedPreferencesManager.getInt(Constants.USER_ID))

        viewModel.getUserData(token,userIdModel).observe(this){
            usersData ->
                findViewById<TextView>(R.id.headerUserName).text = usersData.userData.name
                findViewById<TextView>(R.id.headerUserEmail).text = usersData.userData.email

        }
    }

    private fun setBadges(navView: BottomNavigationView) {
        val cartBadge = navView.getOrCreateBadge(R.id.navigation_cart)
        val notifyBadge = navView.getOrCreateBadge(R.id.navigation_notifications)
        notifyBadge.isVisible = true
        cartBadge.isVisible = true
        cartBadge.number = 3
        notifyBadge.number = 10
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun logout(token:String,userIdModel: UserIdModel){
        val call: Call<MessageResponse> = RetrofitClient.apiService.logout(token,userIdModel)
        call.enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>
            ) {
                Log.d("TAG", "getUserDatas: ${response.code()}")
                if (response.isSuccessful) {
                    sharedPreferencesManager.clearSharedPreferences()
                    Toast.makeText(context,"${response.body()?.message}",Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "getUserDatas: ${response.body()}")
                    val intent = Intent(context, SignupActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Log.d("TAG", "getUserDatas: $t")
            }
        })
    }

}