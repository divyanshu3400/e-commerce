package com.example.navigationdrawer.model

import android.graphics.drawable.Drawable


data class ExpandableMenuItem(
    val id: Int,
    val iconDrawable: Drawable?,
    val title: String,
    val subItems: List<SubMenuItem> = emptyList(),
    var isExpanded: Boolean = false
)
