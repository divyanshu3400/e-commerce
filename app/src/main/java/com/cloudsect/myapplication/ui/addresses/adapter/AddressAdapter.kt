package com.cloudsect.myapplication.ui.addresses.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.AddressLayoutDesignBinding
import com.cloudsect.myapplication.ui.addresses.model.AddressModel

class AddressAdapter(private val context: Context, private val itemList:List<AddressModel>):RecyclerView.Adapter<AddressAdapter.AddressViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = AddressLayoutDesignBinding.inflate(LayoutInflater.from(context),parent,false)

        return AddressViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class AddressViewHolder(private val binding:AddressLayoutDesignBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(addressModel: AddressModel){
            binding.modal=addressModel
            binding.executePendingBindings()
        }
    }
}