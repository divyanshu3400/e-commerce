package com.cloudsect.myapplication.ui.product_detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.databinding.RatingReviewsLayoutBinding
import com.cloudsect.myapplication.ui.product_detail.model.ColorModel

class RatingReviewsAdapter(val context: Context, val listItem: List<ColorModel>) :
    RecyclerView.Adapter<RatingReviewsAdapter.RatingReviewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingReviewsViewHolder {
        val binding =
            RatingReviewsLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return RatingReviewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: RatingReviewsViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    inner class RatingReviewsViewHolder(val binding: RatingReviewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(colorModel: ColorModel) {

        }
    }
}