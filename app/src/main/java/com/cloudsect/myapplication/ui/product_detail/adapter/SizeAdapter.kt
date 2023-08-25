package com.cloudsect.myapplication.ui.product_detail.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.SizeLayoutBinding
import com.cloudsect.myapplication.ui.categories.adapter.CategoriesBrandAdapter
import com.google.android.material.snackbar.Snackbar


class SizeAdapter(private val context: Context,
                  private var sizes: List<String>,
    private val onItemListener: SizeAdapter.OnItemListener
) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {
    private var selectedPosition: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SizeLayoutBinding.inflate(inflater, parent, false)
        return SizeViewHolder(binding,onItemListener)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        Log.d("TAG", "sizes["+position+"]: "+sizes[position])
        holder.bind(sizes[position],position)
    }

    override fun getItemCount(): Int {
        return sizes.size
    }

    inner class SizeViewHolder(private val binding: SizeLayoutBinding,
                               private val onItemListener: SizeAdapter.OnItemListener) :
        RecyclerView.ViewHolder(binding.root)
    ,View.OnClickListener{
        private var sizeName = ""
        fun bind(size: String,position: Int) {
            binding.sizes = size
            sizeName = size
            if (selectedPosition == position) {
                binding.sizeTV.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.black)
                )
            } else {
                binding.sizeTV.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.dark_gray)
                )
            }
            binding.cv.setOnClickListener(this)
            binding.executePendingBindings()
        }

        override fun onClick(view: View) {
            selectedPosition = adapterPosition
            notifyDataSetChanged()
            onItemListener.setProductAccordingToSize(sizeName,selectedPosition, view)
        }
    }

    interface OnItemListener {
        fun setProductAccordingToSize(sizeName:String,adapterPos: Int, view: View)
    }

}
