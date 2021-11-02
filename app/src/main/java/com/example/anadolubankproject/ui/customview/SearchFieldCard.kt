package com.example.anadolubankproject.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.anadolubankproject.R
import com.example.anadolubankproject.databinding.LayoutSearchFieldBinding
import com.example.anadolubankproject.ui.ktx.hideKeyboard
import com.example.anadolubankproject.ui.ktx.onTextChanged
import com.example.anadolubankproject.ui.ktx.showKeyboard
import com.jakewharton.rxrelay2.PublishRelay

class SearchFieldCard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutSearchFieldBinding =
        LayoutSearchFieldBinding.inflate(LayoutInflater.from(context),this)

    private var _hint: String = context.resources.getString(R.string.search)
    var hint: String
        get() = _hint
        set(value) {
            _hint = value
            binding.etSearchQuery.hint = value
        }
    var query: String
        get() = if (binding.etSearchQuery.text.isEmpty() || binding.etSearchQuery.text.isBlank()) "" else binding.etSearchQuery.text.toString()
        set(value) {
            binding.etSearchQuery.setText(
                    if (value.isEmpty() || value.isBlank()) {
                        binding.ivClear.isVisible = false
                        ""
                    } else {
                        binding.ivClear.isVisible = true
                        value
                    }
            )
        }
    var onTextChangedListener: ((s: String) -> Unit)? = null
    var clearOnclickListener: ((view: View) -> Unit)? = null
    val publisher: PublishRelay<String> = PublishRelay.create()

    init {
        attrs?.let { initializeAttributes(attrs) }
        binding.ivClear.setOnClickListener { binding.etSearchQuery.setText("")
        clearOnclickListener?.invoke(it)}
        setOnClickListener { binding.etSearchQuery.performClick() }
        binding.etSearchQuery.onTextChanged { s, start, before, count ->
            binding.ivClear.isVisible = s.toString().isNotEmpty()
            publisher.accept(s.toString())
            onTextChangedListener?.invoke(s.toString())
        }
    }

    private fun initializeAttributes(attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.search_field_card, 0, 0)
        try {
            hint = typedArray.getString(R.styleable.search_field_card_android_hint)
                    ?: context.resources.getString(R.string.search)
        } finally {
            typedArray.recycle()
        }
    }

    fun showKeyboard() {
        binding.etSearchQuery.showKeyboard()
    }

    fun hideKeyboard() {
        binding.etSearchQuery.hideKeyboard()
    }
}