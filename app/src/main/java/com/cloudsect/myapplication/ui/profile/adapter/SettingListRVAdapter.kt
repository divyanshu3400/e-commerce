package com.cloudsect.myapplication.ui.profile.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ProfileSettingLayoutBinding
import com.cloudsect.myapplication.ui.profile.ListItemModel
import com.cloudsect.myapplication.ui.profile.listeners.Listeners
import com.cloudsect.myapplication.ui.wishlist.WishlistActivity


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
            if (listItemModel.id==1){
                Navigation.findNavController(itemView).navigate(R.id.action_navigation_profile_to_personalDetailFragment)
            }
            else if (listItemModel.id==2){
                val intent = Intent(context, WishlistActivity::class.java)
                context.startActivity(intent)
            }
            else if (listItemModel.id==3){
                val intent = Intent(context, WishlistActivity::class.java)
                context.startActivity(intent)
            }
//            Toast.makeText(context,"Clicked: "+listItemModel.title,Toast.LENGTH_SHORT).show()
        }
    }
}
