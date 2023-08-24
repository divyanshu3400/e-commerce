package com.cloudsect.myapplication.ui.personal_detail

import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.cloudsect.myapplication.R

interface OnItemClickListener {
    fun radioButtonClickEvent(view: View)
    fun openMedia(view: View)
}