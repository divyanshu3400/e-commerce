package com.cloudsect.myapplication.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.ui.profile.UserProfileModel.Companion.loadImage
import com.cloudsect.myapplication.databinding.FragmentProfileBinding
import com.cloudsect.myapplication.ui.profile.adapter.SettingListRVAdapter


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSettingItems()

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initSettingItems() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView2.layoutManager = LinearLayoutManager(context)

        val itemList = mutableListOf(
            ListItemModel(1,resources.getDrawable(R.drawable.ic_profile), "Personal Details"),
            ListItemModel(2,resources.getDrawable(R.drawable.ic_myorders), "My Orders"),
            ListItemModel(3,resources.getDrawable(R.drawable.ic_wishlist), "My Favorites"),
            ListItemModel(4,resources.getDrawable(R.drawable.ic_shipping), "Shipping Address"),
            ListItemModel(5,resources.getDrawable(R.drawable.ic_credit_card), "My Card"),
            ListItemModel(6,resources.getDrawable(R.drawable.ic_settings), "Settings"),
        )

        val adapter = context?.let { SettingListRVAdapter(it, itemList) }
        binding.recyclerView.adapter = adapter

        val itemList2 = mutableListOf(
            ListItemModel(7,resources.getDrawable(R.drawable.warning2), "FAQs"),
            ListItemModel(8,resources.getDrawable(R.drawable.ic_privacy_policy), "Privacy Policy"),
            ListItemModel(9,resources.getDrawable(R.drawable.ic_settings), "Terms & Conditions"),
            ListItemModel(10,resources.getDrawable(R.drawable.ic_shipping), "About Us"),
            ListItemModel(11,resources.getDrawable(R.drawable.ic_credit_card), "Contact Us"),
        )

        val adapter2 = context?.let { SettingListRVAdapter(it, itemList2) }
        binding.recyclerView2.adapter = adapter2

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val userProfileModel = UserProfileModel()
        userProfileModel.userName = "Divyanshu Kumar Kushwaha"
        userProfileModel.userEmail = "nddictator@gmail.com"
        userProfileModel.userProfileImage = loadImage(
            binding.ivProfileImage,
            "data:image/jpeg;base64,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"
        )
        binding.userProfileModel = userProfileModel
    }

}