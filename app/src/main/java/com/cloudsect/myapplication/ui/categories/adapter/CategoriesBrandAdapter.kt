package com.cloudsect.myapplication.ui.categories.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.CategoriesBrandLayoutBinding
import com.cloudsect.myapplication.ui.categories.model.CategoryResponse
import com.cloudsect.myapplication.ui.home.BrandModel.Companion.loadImage

class CategoriesBrandAdapter(
    private val context: Context,
    private val itemList: List<CategoryResponse>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CategoriesBrandAdapter.BrandViewHolder>() {

    private var selectedPosition: Int = 0

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
    ) : RecyclerView.ViewHolder(binding.root) {
        private  var adapterPos=0
        fun bind(categoryResponse: CategoryResponse, position: Int) {
            adapterPos=position
            Log.d("TAG", "bind: ${categoryResponse.nav_name}")
            binding.categoryResponse = categoryResponse
            if (selectedPosition == position) {
                binding.brandTitle.setTextColor(ContextCompat.getColor(context, R.color.yellow))
                binding.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.forest_green))
            } else {

                binding.brandTitle.setTextColor(
                    ContextCompat.getColor(context, android.R.color.black)
                )
                binding.parent.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent))

            }
            loadImage(binding.brandImageVIew, categoryResponse.nav_logo)
            binding.executePendingBindings()
            binding.parent.setOnClickListener{
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                onItemListener.setChildCategoryAdapter(categoryResponse.nav_id)
            }
        }

    }

    interface OnItemListener {
        fun setChildCategoryAdapter(id: Int)
    }
}