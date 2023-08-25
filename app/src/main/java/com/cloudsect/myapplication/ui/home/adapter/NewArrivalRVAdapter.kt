package com.cloudsect.myapplication.ui.home.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.NewArrivalsLayoutBinding
import com.cloudsect.myapplication.ui.wishlist.model.WishlistProductModel

class NewArrivalRVAdapter(private val context: Context,private val itemList: List<WishlistProductModel>):
    RecyclerView.Adapter<NewArrivalRVAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = NewArrivalsLayoutBinding.inflate(LayoutInflater.from(context))

        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind(itemList[position])
    }

    inner class ProductViewHolder(val binding: NewArrivalsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modal: WishlistProductModel){
            binding.modal = modal
            binding.executePendingBindings()
            val bundle = Bundle()
            bundle.putSerializable("product", modal)
            binding.parent.setOnClickListener {
                val navOptions = NavOptions.Builder()
                    .setEnterAnim(android.R.animator.fade_in)
                    .setExitAnim(android.R.animator.fade_out)
                    .setPopEnterAnim(android.R.animator.fade_in)
                    .setPopExitAnim(android.R.animator.fade_out)
                    .build()

                Navigation.findNavController(itemView).navigate(
                    R.id.productDetailsFragment,
                    bundle,
                    navOptions
                )
            }
        }
    }
}