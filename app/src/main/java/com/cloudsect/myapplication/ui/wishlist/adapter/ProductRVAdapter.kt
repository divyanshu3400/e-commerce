package com.cloudsect.myapplication.ui.wishlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.WishlistDesignLayoutBinding
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

class ProductRVAdapter(private val context: Context,private val modalList:List<WishlistProductModel>) : RecyclerView.Adapter<ProductRVAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = WishlistDesignLayoutBinding.inflate(LayoutInflater.from(context),parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modalList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(modalList[position])
    }

    inner class ProductViewHolder(val binding:WishlistDesignLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(modal:WishlistProductModel){
                binding.modal=modal
                binding.executePendingBindings()
            }

    }
}