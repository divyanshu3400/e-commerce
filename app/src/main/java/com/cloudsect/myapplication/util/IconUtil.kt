package com.cloudsect.myapplication.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

class IconUtil {

    companion object{
        fun drawableToBitmap(drawable: Drawable): Bitmap {
            val width = drawable.intrinsicWidth
            val height = drawable.intrinsicHeight
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }

    }
}