package com.cloudsect.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.databinding.ExpandableMenuItemBinding
import com.cloudsect.myapplication.util.slideDown
import com.cloudsect.myapplication.util.slideUp
import com.example.navigationdrawer.model.ExpandableMenuItem
import com.example.navigationdrawer.model.SubMenuItem

class ExpandableMenuAdapter(
    private val items: List<ExpandableMenuItem>,
    private val onParentItemOnClickListener: ParentItemListener,
    private val onSubItemClickListener: (SubMenuItem) -> Unit
) : RecyclerView.Adapter<ExpandableMenuAdapter.ViewHolder>() {

    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ExpandableMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(parent.context, binding, onParentItemOnClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.subItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = holder.subItemsAdapter
        }

        holder.subItemsAdapter.updateData(item.subItems)
//        holder.subItemsAdapter.updateData(item.subItems.map { SubMenuItem(it) })

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(
        private val context: Context,
        private val binding: ExpandableMenuItemBinding,
        private val onParentItemOnClickListener: ParentItemListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(expandableMenuItem: ExpandableMenuItem) {
            binding.menuItem = expandableMenuItem
            binding.menuItem = expandableMenuItem

            // Check if the item is selected based on the adapter position
            val isSelected = selectedPosition == adapterPosition

            // Set the text and background color based on selection
            if (isSelected) {
                binding.textTitle.setTextColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.white
                    )
                )
                binding.root.background =
                    ContextCompat.getDrawable(context, R.drawable.navigation_selected_background)
            } else {
                binding.textTitle.setTextColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.black
                    )
                )
                binding.root.background =
                    ContextCompat.getDrawable(context, android.R.color.transparent)
            }

            // Handle item click
            binding.root.setOnClickListener {
                selectedPosition = adapterPosition

                if (expandableMenuItem.subItems.isEmpty()) {
                    onParentItemOnClickListener.onParentItemClick(expandableMenuItem)
                } else {
                    if (expandableMenuItem.isExpanded) {
                        binding.subItemsRecyclerView.slideUp()
                    } else {
                        binding.subItemsRecyclerView.slideDown()
                    }
                    expandableMenuItem.isExpanded = !expandableMenuItem.isExpanded
                }
            }
        }

        val subItemsRecyclerView: RecyclerView = binding.subItemsRecyclerView
        val subItemsAdapter: SubItemAdapter = SubItemAdapter(emptyList()) { subItem ->
            onSubItemClickListener(subItem)
        }
    }

    interface ParentItemListener {
        fun onParentItemClick(expandableMenuItem: ExpandableMenuItem)
    }

}
