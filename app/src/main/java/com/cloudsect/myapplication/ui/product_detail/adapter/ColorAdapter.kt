package com.cloudsect.myapplication.ui.product_detail.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ColorLayoutBinding
import com.cloudsect.myapplication.databinding.SizeLayoutBinding
import com.cloudsect.myapplication.ui.product_detail.model.ColorModel


class ColorAdapter(private val context: Context,
                  private var sizes: List<ColorModel>,
                  private val onItemListener: ColorAdapter.OnItemListener
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    private var selectedPosition: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ColorLayoutBinding.inflate(inflater, parent, false)
        return ColorViewHolder(binding,onItemListener)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        Log.d("TAG", "sizes["+position+"]: "+sizes[position])
        holder.bind(sizes[position],position)
    }

    override fun getItemCount(): Int {
        return sizes.size
    }

    inner class ColorViewHolder(private val binding: ColorLayoutBinding,private val onItemListener: ColorAdapter.OnItemListener) : RecyclerView.ViewHolder(binding.root)
        ,View.OnClickListener{
        private var color =""
        fun bind(model: ColorModel,position: Int) {
            color = model.colorName
            binding.cvChild.setCardBackgroundColor(Color.parseColor(model.colorHex))
            if (selectedPosition == position) {
                binding.parent.background = ContextCompat.getDrawable(context,R.drawable.selected_color_background)
            }
            else {
                binding.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }
            binding.parent.setOnClickListener(this)
            binding.executePendingBindings()
        }

        override fun onClick(view: View) {
            selectedPosition = adapterPosition
            notifyDataSetChanged()
            onItemListener.setProductAccordingToColor(color,selectedPosition, view)
        }
    }

    interface OnItemListener {
        fun setProductAccordingToColor(color:String,adapterPos: Int, view: View)
    }

}
