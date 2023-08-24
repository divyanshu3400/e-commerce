package com.cloudsect.myapplication.ui.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.BrandLayoutBinding
import com.cloudsect.myapplication.ui.home.BrandModel
import com.cloudsect.myapplication.ui.home.BrandModel.Companion.loadImage

class BrandLogoRVAdapter(private val context: Context, private val itemList: List<BrandModel>):
    RecyclerView.Adapter<BrandLogoRVAdapter.BrandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = BrandLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return BrandViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class BrandViewHolder(private val binding:BrandLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(brandModel: BrandModel){
            binding.brandModel=brandModel
            loadImage(binding.brandImageVIew, brandModel.brandImage)
            binding.executePendingBindings()
        }
    }

}