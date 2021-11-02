package com.example.anadolubankproject.ui.customview

import android.content.Context
import android.util.AttributeSet
import com.example.anadolubankproject.R
import com.google.android.material.card.MaterialCardView

open class MCVBorderless @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialCardView(context, attrs, defStyleAttr) {

    init {
        radius = context.resources.getDimension(R.dimen.radius_4)
        cardElevation = 0f
    }


}