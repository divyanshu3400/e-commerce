package com.cloudsect.myapplication.ui.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.CategoriesBrandLayoutBinding
import com.cloudsect.myapplication.ui.home.BrandModel
import com.cloudsect.myapplication.ui.home.BrandModel.Companion.loadImage

class ChildCategoriesBrandAdapter(
    private val context: Context,
    private val itemList: List<BrandModel>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<ChildCategoriesBrandAdapter.BrandViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = CategoriesBrandLayoutBinding.inflate(
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
        holder.bind(itemList[position], position)
    }

    inner class BrandViewHolder(
        private val binding: CategoriesBrandLayoutBinding,
        private val onItemListener: OnItemListener,
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var brandId=0
        private var brandTitle=""
        fun bind(brandModel: BrandModel, position: Int) {
            binding.brandModel = brandModel
            brandId=brandModel.brandId
            brandTitle=brandModel.brandTitle
            loadImage(binding.brandImageVIew, brandModel.brandImage)
            binding.executePendingBindings()
            binding.parent.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onItemListener.getProduct(brandTitle,brandId, view)
        }
    }

    interface OnItemListener {
        fun getProduct(brandTitle: String,brandId:Int, view: View)
    }
}