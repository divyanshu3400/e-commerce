package com.cloudsect.myapplication.ui.myorders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.MyOrdersLayoutBinding
import com.cloudsect.myapplication.ui.myorders.model.MyOrderModal
import com.cloudsect.myapplication.ui.myorders.model.MyOrderModal.Companion.loadImage

class MyOrdersAdapter(private val context: Context, private val itemList:List<MyOrderModal>)
    : RecyclerView.Adapter<MyOrdersAdapter.MyOrdersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrdersViewHolder {
        val binding = MyOrdersLayoutBinding.inflate(LayoutInflater.from(context),parent,false)

        return MyOrdersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyOrdersViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class MyOrdersViewHolder(val binding: MyOrdersLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(myOrderModal: MyOrderModal)
        {
            binding.modal=myOrderModal
            loadImage(binding.productImage, myOrderModal.productImageUrl)
            binding.executePendingBindings()
        }

    }

}