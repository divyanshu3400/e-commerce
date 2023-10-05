package com.cloudsect.myapplication.util

import com.cloudsect.myapplication.ui.product_detail.model.ColorModel

class Colors {

    companion object{

        @JvmStatic
        fun getColorList(): List<ColorModel> {
            val colorModel = ColorModel(1,"black","#000000")
            return mutableListOf(colorModel)
        }

    }
}