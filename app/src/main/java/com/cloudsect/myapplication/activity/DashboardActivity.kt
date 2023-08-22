package com.cloudsect.myapplication.activity

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ActivityDashboardBinding
import com.cloudsect.myapplication.util.BadgeDrawable.Companion.setBadgeCount
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
        val badgeTextView = findViewById<TextView>(R.id.notificationBadge)
        val newNotificationCount = 5 // Replace with your dynamic notification count

//        badgeTextView.text = newNotificationCount.toString()
//        badgeTextView.setText("7")
//        badgeTextView.visibility = if (newNotificationCount > 0) View.VISIBLE else View.GONE

        val bottomNavView = findViewById<BottomNavigationView>(R.id.nav_view)
        val notificationBadgeContainer = bottomNavView.menu.findItem(R.id.navigation_notifications).actionView as FrameLayout
        val cartBadgeContainer = bottomNavView.menu.findItem(R.id.navigation_cart).actionView as FrameLayout

        // Inflate the badge layout into the badge containers
        val inflater = LayoutInflater.from(this)
        inflater.inflate(R.layout.badge_layout, notificationBadgeContainer, true)
        inflater.inflate(R.layout.badge_layout, cartBadgeContainer, true)

        // Get the badge TextViews inside the containers
        val notificationBadgeTextView = notificationBadgeContainer.findViewById<TextView>(R.id.notificationBadge)
        val cartBadgeTextView = cartBadgeContainer.findViewById<TextView>(R.id.notificationBadge)

        // Update badge counts and visibility
        val notificationCount = 5 // Replace with actual notification count
        val cartItemCount = 3 // Replace with actual cart item count

        notificationBadgeTextView.text = notificationCount.toString()
        notificationBadgeTextView.visibility = if (notificationCount > 0) TextView.VISIBLE else TextView.GONE

        cartBadgeTextView.text = cartItemCount.toString()
        cartBadgeTextView.visibility = if (cartItemCount > 0) TextView.VISIBLE else TextView.GONE


        navView.setupWithNavController(navController)
    }
}