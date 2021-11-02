package com.example.anadolubankproject.ui.ktx

import android.view.animation.Animation

const val ANIMATION_DURATION_MEDIUM: Long = 400
const val ANIMATION_DURATION_LONG: Long = 800
const val ANIMATION_DURATION_SHORT: Long = 200

fun Animation.onAnimationEnd(predicate: (animation: Animation?) -> Unit) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}
        override fun onAnimationEnd(animation: Animation?) {
            predicate.invoke(animation)
        }

        override fun onAnimationStart(animation: Animation?) {}
    })
}