package com.cloudsect.myapplication.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)

        navView.setupWithNavController(navController)

        val cartBadge = navView.getOrCreateBadge(R.id.navigation_cart)
        val notifBadge = navView.getOrCreateBadge(R.id.navigation_notifications)
        notifBadge.isVisible = true
        cartBadge.isVisible = true
        cartBadge.number = 3
        notifBadge.number = 10
    }

}

