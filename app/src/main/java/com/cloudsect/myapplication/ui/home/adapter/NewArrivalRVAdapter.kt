package com.cloudsect.myapplication.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.NewArrivalsLayoutBinding
import com.cloudsect.myapplication.ui.categories.model.ProductResponse

open class NewArrivalRVAdapter(
    private val context: Context,
    private val itemList: ArrayList<ProductResponse>,
    private val onWishlistCheckChangedListener: OnWishlistCheckChangedListener,
    private val onParentClickListener: OnParentClickListener
):
    RecyclerView.Adapter<NewArrivalRVAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = NewArrivalsLayoutBinding.inflate(LayoutInflater.from(context))

        return ProductViewHolder(binding,onWishlistCheckChangedListener,onParentClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind(itemList[position])
    }

    inner class ProductViewHolder(
        val binding: NewArrivalsLayoutBinding,
        private val onWishlistCheckChangedListener: OnWishlistCheckChangedListener,
        private  val onParentClickListener: OnParentClickListener
        ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modal: ProductResponse){
            binding.modal = modal
            binding.executePendingBindings()
            binding.parent.setOnClickListener {
                onParentClickListener.onClick(modal)
            }
            binding.addToWishlist?.setOnCheckedChangeListener { button, b ->
                onWishlistCheckChangedListener.onChange(b,context,modal.product_id)
            }
        }
    }

    interface OnWishlistCheckChangedListener{
        fun onChange(isChanged:Boolean,context: Context,productId:Int)
    }

    interface OnParentClickListener{
        fun onClick(productResponse: ProductResponse)
    }

}