package com.cloudsect.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloudsect.myapplication.R
import com.cloudsect.myapplication.search.SuggestionEntity

class SuggestionAdapter(private var suggestions: List<SuggestionEntity>, private val onItemListener:SuggestionAdapter.OnItemClickListener) :
    RecyclerView.Adapter<SuggestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_suggestion, parent, false)
        return ViewHolder(itemView,onItemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        holder.bind(suggestion)
    }

    override fun getItemCount(): Int = suggestions.size

    fun updateSuggestions(newSuggestions: List<SuggestionEntity>) {
        suggestions = newSuggestions
        notifyDataSetChanged()
    }
    fun clearAdapterList() {
        suggestions= emptyList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, private val onItemListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val suggestionTextView: TextView = itemView.findViewById(R.id.suggestionTextView)

        fun bind(suggestion: SuggestionEntity) {
            suggestionTextView.text = suggestion.suggestionText
            itemView.setOnClickListener { onItemClick(suggestion,itemView) }
        }

        private fun onItemClick(suggestion: SuggestionEntity,view: View) {
            onItemListener.performSearch(suggestion,itemView)
        }
    }
    interface OnItemClickListener{
        fun performSearch(brandTitle:SuggestionEntity, view: View)
    }
}
