package com.cloudsect.myapplication.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import androidx.core.content.ContextCompat
import com.cloudsect.myapplication.R

class BadgeDrawable(private val context: Context) : Drawable() {

    private val mBadgePaint = Paint()
    private val mBadgePaint1 = Paint()
    private val mTextPaint = Paint()
    private val mTxtRect = Rect()

    private var mCount = ""
    private var mWillDraw = false

    init {
        val mTextSize = context.resources.getDimension(R.dimen.badge_text_size)

        mBadgePaint.apply {
            color = Color.RED
            isAntiAlias = true
            style = Paint.Style.FILL
        }

        mBadgePaint1.apply {
            color = ContextCompat.getColor(context.applicationContext, R.color.yellow)
            isAntiAlias = true
            style = Paint.Style.FILL
        }

        mTextPaint.apply {
            color = Color.WHITE
            typeface = Typeface.DEFAULT
            textSize = mTextSize
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
    }

    override fun draw(canvas: Canvas) {
        if (!mWillDraw) {
            return
        }

        val bounds = bounds
        val width = (bounds.right - bounds.left).toFloat()
        val height = (bounds.bottom - bounds.top).toFloat()

        val radius = (Math.max(width, height) / 2) / 2
        val centerX = (width - radius - 1) + 5
        val centerY = radius - 5

        if (mCount.length <= 2) {
            canvas.drawCircle(centerX, centerY, (radius + 7.5).toFloat(), mBadgePaint1)
            canvas.drawCircle(centerX, centerY, (radius + 5.5).toFloat(), mBadgePaint)
        } else {
            canvas.drawCircle(centerX, centerY, (radius + 8.5).toFloat(), mBadgePaint1)
            canvas.drawCircle(centerX, centerY, (radius + 6.5).toFloat(), mBadgePaint)
        }

        mTextPaint.getTextBounds(mCount, 0, mCount.length, mTxtRect)
        val textHeight = mTxtRect.bottom - mTxtRect.top.toFloat()
        val textY = centerY + (textHeight / 2)
        if (mCount.length > 2) {
            canvas.drawText("99+", centerX, textY, mTextPaint)
        } else {
            canvas.drawText(mCount, centerX, textY, mTextPaint)
        }
    }

    /*
        Sets the count (i.e notifications) to display.
     */
    fun setCount(count: String) {
        mCount = count

        // Only draw a badge if there are notifications.
        mWillDraw = !count.equals("0", ignoreCase = true)
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        // do nothing
    }

    override fun setColorFilter(cf: android.graphics.ColorFilter?) {
        // do nothing
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int {
        return android.graphics.PixelFormat.UNKNOWN
    }

    companion object {
        @JvmStatic
        fun setBadgeCount(context: Context, icon: LayerDrawable, count: String) {
            val badge: BadgeDrawable
            val reuse: Drawable? = icon.findDrawableByLayerId(R.id.ic_badge)
            badge = if (reuse != null && reuse is BadgeDrawable) {
                reuse
            } else {
                BadgeDrawable(context)
            }
            badge.setCount(count)
            icon.mutate()
            icon.setDrawableByLayerId(R.id.ic_badge, badge)
        }
    }

}
