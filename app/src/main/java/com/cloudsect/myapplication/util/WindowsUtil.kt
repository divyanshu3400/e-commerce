package com.cloudsect.myapplication.util

import android.content.Context
import kotlin.math.pow
import kotlin.math.sqrt

class WindowsUtil {
    companion object{

        fun isTablet(context: Context): Boolean {
            val displayMetrics = context.resources?.displayMetrics
            val screenWidthPixels = displayMetrics?.widthPixels ?: 0
            val screenHeightPixels = displayMetrics?.heightPixels ?: 0

            val screenWidthInches = screenWidthPixels / displayMetrics?.xdpi!!
            val screenHeightInches = screenHeightPixels / displayMetrics.ydpi

            val screenDiagonalInches = sqrt(
                screenWidthInches.toDouble().pow(2.0) + screenHeightInches.toDouble().pow(2.0)
            ).toInt()
            val screenSizeThresholdInches = 7.0
            return screenDiagonalInches >= screenSizeThresholdInches
        }
    }
}