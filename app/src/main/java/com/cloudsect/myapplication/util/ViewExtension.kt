package com.cloudsect.myapplication.util

import android.view.View
import android.view.animation.TranslateAnimation
import android.view.animation.Animation

fun View.slideDown() {
    val animate = TranslateAnimation(0f, 0f, -height.toFloat(), 0f)
    animate.duration = 500
    animate.fillAfter = true
    this.startAnimation(animate)
    this@slideDown.visibility = View.VISIBLE
}

fun View.slideUp() {
    val animate = TranslateAnimation(0f, 0f, 0f, -height.toFloat()+2f)
    animate.duration = 400
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationRepeat(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            this@slideUp.visibility = View.GONE
        }
    })
    this.startAnimation(animate)

}
