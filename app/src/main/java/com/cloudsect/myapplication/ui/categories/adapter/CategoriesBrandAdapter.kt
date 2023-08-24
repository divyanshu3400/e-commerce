package com.cloudsect.myapplication.ui.categories.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.CategoriesBrandLayoutBinding
import com.cloudsect.myapplication.ui.home.BrandModel
import com.cloudsect.myapplication.ui.home.BrandModel.Companion.loadImage

class CategoriesBrandAdapter(
    private val context: Context,
    private val itemList: List<BrandModel>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CategoriesBrandAdapter.BrandViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

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
        private  var adapterPos=0
        fun bind(brandModel: BrandModel, position: Int) {
            adapterPos=position
            binding.brandModel = brandModel
            if (selectedPosition == position) {
                binding.brandTitle.setTextColor(
                    ContextCompat.getColor(context, R.color.yellow)
                )
            } else {
                binding.brandTitle.setTextColor(
                    ContextCompat.getColor(context, android.R.color.black)
                )
            }
            loadImage(binding.brandImageVIew, brandModel.brandImage)
            binding.executePendingBindings()
            binding.parent.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            selectedPosition = adapterPosition
            notifyDataSetChanged()
            onItemListener.setChildCategoryAdapter(adapterPos, view)
        }
    }

    interface OnItemListener {
        fun setChildCategoryAdapter(adapterPos: Int, view: View)
    }
}