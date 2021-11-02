package com.example.anadolubankproject.ui.loader

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isInvisible
import com.example.anadolubankproject.R
import com.example.anadolubankproject.databinding.DialogLoadingBinding
import com.example.anadolubankproject.ui.ktx.ANIMATION_DURATION_LONG
import com.example.anadolubankproject.ui.ktx.ANIMATION_DURATION_MEDIUM
import com.example.anadolubankproject.ui.ktx.onAnimationEnd

class LoadingDialog(context: Context) : AppCompatDialog(context) {

    private var binding: DialogLoadingBinding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
    private val fadeInAnimation: Animation by lazy { AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in).apply { duration = ANIMATION_DURATION_LONG } }
    private val fadeOutAnimation: Animation by lazy { AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out).apply { duration = ANIMATION_DURATION_MEDIUM } }
    private val iconList: ArrayList<Int> by lazy {
        ArrayList<Int>().apply {
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
            add(R.drawable.anadolubank_logo)
        }
    }
    var isActive: Boolean = false
        private set

    init {

        window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setWindowAnimations(R.style.Widget_AnandoluBank_LoadingDialogAnimation)
        }
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
    }

    override fun onBackPressed() {
        dismiss()
        ownerActivity?.onBackPressed()
        super.onBackPressed()
    }

    override fun dismiss() {
        isActive = false
        super.dismiss()
    }

    override fun show() {
        super.show()
        isActive = true
        startAnimation()
    }

    private fun startAnimation() {
        var index = 0

        fadeInAnimation.onAnimationEnd {
            if (!isShowing || !isActive) return@onAnimationEnd

            Handler(Looper.getMainLooper()).postDelayed({
                if (isActive)
                    binding.ivIcon.startAnimation(fadeOutAnimation)
            },context.resources.getInteger(R.integer.loading_dialog_delay).toLong())
        }
        fadeOutAnimation.onAnimationEnd {
            if (!isShowing || !isActive) return@onAnimationEnd
            index++
            index %= iconList.size
            binding.ivIcon.isInvisible = true
            binding.ivIcon.setImageResource(iconList[index])

            Handler(Looper.getMainLooper()).postDelayed({
                if (isActive)
                    binding.ivIcon.startAnimation(fadeOutAnimation)
            }, 0)
        }

    }
}