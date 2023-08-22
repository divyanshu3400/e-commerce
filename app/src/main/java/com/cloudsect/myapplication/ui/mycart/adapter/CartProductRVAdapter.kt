package com.cloudsect.myapplication.ui.wishlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.CartItemLayoutBinding
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

class CartProductRVAdapter(private val context: Context,private val modalList:List<WishlistProductModel>) :
    RecyclerView.Adapter<CartProductRVAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CartItemLayoutBinding.inflate(LayoutInflater.from(context),parent, false)
        return ProductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(modalList[position])
    }


    override fun getItemCount(): Int {
        return modalList.size
    }


    inner class ProductViewHolder(val binding:CartItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(modal:WishlistProductModel){
                binding.modal=modal
                binding.executePendingBindings()
            }

    }
}