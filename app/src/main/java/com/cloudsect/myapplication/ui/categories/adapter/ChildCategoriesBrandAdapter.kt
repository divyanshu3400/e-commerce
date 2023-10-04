package com.cloudsect.myapplication.ui.categories.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.ChildCategoryBrandLayoutBinding
import com.cloudsect.myapplication.ui.categories.model.ChildCatRes
import com.cloudsect.myapplication.ui.categories.model.ChildCatRes.Companion.loadImage

class ChildCategoriesBrandAdapter(
    private val context: Context,
    private val itemList: List<ChildCatRes>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<ChildCategoriesBrandAdapter.BrandViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = ChildCategoryBrandLayoutBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return BrandViewHolder(binding, onItemListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class BrandViewHolder(
        private val binding: ChildCategoryBrandLayoutBinding,
        private val onItemListener: OnItemListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(childCatRes: ChildCatRes) {
            binding.categoryResponse = childCatRes
            Log.d("TAG", "http  ${childCatRes.pcat_logos}: ")
            loadImage(binding.brandImageVIew, childCatRes.pcat_logos)
            binding.executePendingBindings()
            binding.parent.setOnClickListener{
                onItemListener.getProduct(childCatRes)

            }
        }
    }

    interface OnItemListener {
        fun getProduct(childCatRes: ChildCatRes)
    }
}