package com.cloudsect.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.ItemSubMenuBinding
import com.example.navigationdrawer.model.SubMenuItem


class SubItemAdapter(
    private var subItems: List<SubMenuItem>,
    private val onItemClick: (SubMenuItem) -> Unit
) : RecyclerView.Adapter<SubItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSubMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(subItems[position]) }

    override fun getItemCount(): Int {
        return subItems.size
    }

    fun updateData(newSubItems: List<SubMenuItem>) {
        subItems = newSubItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemSubMenuBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subMenuItem: SubMenuItem){
            binding.subMenuItem = subMenuItem

            binding.root.setOnClickListener { onItemClick(subMenuItem) }

        }
    }

}
