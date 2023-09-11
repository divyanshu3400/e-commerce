package com.cloudsect.myapplication.ui.profile.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ProfileSettingLayoutBinding
import com.cloudsect.myapplication.ui.profile.ListItemModel
import com.cloudsect.myapplication.ui.profile.listeners.Listeners
import com.cloudsect.myapplication.ui.wishlist.WishlistFragment


class SettingListRVAdapter(private val context: Context,private val itemList: List<ListItemModel>) :
    RecyclerView.Adapter<SettingListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProfileSettingLayoutBinding.
        inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])

    }

    override fun getItemCount(): Int = itemList.size

    inner class ViewHolder(private val binding: ProfileSettingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), Listeners {

        fun bind(item: ListItemModel) {
            binding.listItem = item
            binding.executePendingBindings()
            binding.parentLayout.setOnClickListener { parentLayoutClicked(item) }
        }

        override fun parentLayoutClicked(listItemModel: ListItemModel) {
            val navOptions = NavOptions.Builder()
                .setEnterAnim(android.R.animator.fade_in)
                .setExitAnim(android.R.animator.fade_out)
                .setPopEnterAnim(android.R.animator.fade_in)
                .setPopExitAnim(android.R.animator.fade_out)
                .build()
            when (listItemModel.id) {
                1 -> { Navigation.findNavController(itemView).navigate(R.id.personalDetailFragment,null, navOptions) }

                2 -> { Navigation.findNavController(itemView).navigate(R.id.action_navigation_profile_to_myOrdersFragment) }

                3 -> { Navigation.findNavController(itemView).navigate(R.id.action_navigation_profile_to_wishlistFragment) }

                4 -> { Navigation.findNavController(itemView).navigate(R.id.action_navigation_profile_to_addressFragment) }

                else -> {
                    // Handle the default case or additional cases
                }
            }
        }
    }
}
