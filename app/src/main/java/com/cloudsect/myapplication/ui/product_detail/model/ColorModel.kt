package com.cloudsect.myapplication.ui.product_detail.model

import java.io.Serializable

data class ColorModel(
    val color_id: Int,
    val color_name: String,
    val color_hex: String
): Serializable
