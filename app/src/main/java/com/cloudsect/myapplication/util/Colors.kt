package com.cloudsect.myapplication.util

import com.cloudsect.myapplication.ui.product_detail.model.ColorModel

class Colors {

    companion object{

        @JvmStatic
        fun getColorList(): List<ColorModel> {
            val colorModel = ColorModel()
            colorModel.colorName = "black"
            colorModel.colorHex = "#000000"

            val colorModel1 = ColorModel()
            colorModel1.colorName = "purple"
            colorModel1.colorHex = "#673AB7"

            val colorModel2 = ColorModel()
            colorModel2.colorName = "red"
            colorModel2.colorHex = "#F90707"

            val colorModel3 = ColorModel()
            colorModel3.colorName = "maroon"
            colorModel3.colorHex = "#800000"

            val colorModel4 = ColorModel()
            colorModel4.colorName = "pale_green"
            colorModel4.colorHex = "#98FB98"

            val colorModel5 = ColorModel()
            colorModel5.colorName = "medium_violet_red"
            colorModel5.colorHex = "#C71585"

            val colorModel6 = ColorModel()
            colorModel6.colorName = "crimson"
            colorModel6.colorHex = "#DC143C"

            val colorModel7 = ColorModel()
            colorModel7.colorName = "dark_magenta"
            colorModel7.colorHex = "#9400D3"

            val colorModel8 = ColorModel()
            colorModel8.colorName = "sky_blue"
            colorModel8.colorHex = "#87CEEB"

            val colorModel9 = ColorModel()
            colorModel9.colorName = "cyan"
            colorModel9.colorHex = "#00FFFF"

            val colorModel10 = ColorModel()
            colorModel10.colorName = "dark_slate_gray"
            colorModel10.colorHex = "#2F4F4F"

            return mutableListOf(colorModel,colorModel1,colorModel2,colorModel3,colorModel4,colorModel5
                ,colorModel6,colorModel7)
        }

    }
}