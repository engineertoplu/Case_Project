package com.example.anadolubankproject.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.anadolubankproject.R
import com.example.anadolubankproject.databinding.LayoutToolbarBinding
import com.jakewharton.rxrelay2.PublishRelay

class ToolbarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutToolbarBinding =
        LayoutToolbarBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Title
     */
    private var _title: String? = null
    var title: String?
        get() = _title
        set(value) {
            _title = value
            applyTitleText()
        }

    private fun applyTitleText() {
        title?.let { binding.tvTitle.text = it }
    }

    /**
     * _hasSearchField
     */
    private var _hasSearchField: Boolean = false
    var hasSearchField: Boolean
        get() = _hasSearchField
        set(value) {
            _hasSearchField = value
            applyHasSearchField()
        }

    private fun applyHasSearchField() {
        if (hasSearchField) {
            binding.searchField.visibility = View.VISIBLE
        } else
            binding.searchField.visibility = View.GONE
    }

    /**
     * hint
     */
    private var _hint: String? = null
    var hint: String?
        get() = _hint
        set(value) {
            _hint = value
            applyHint()
        }

    private fun applyHint() {
        hint?.let { binding.searchField.hint = it }
    }


    var onSearchQueryChanged: ((s: String) -> Unit)?
        get() = binding.searchField.onTextChangedListener
        set(value) {
            binding.searchField.onTextChangedListener = value
        }

    var onClearClickListener: ((view: View) -> Unit)?
        get() = binding.searchField.clearOnclickListener
        set(value) {
            binding.searchField.clearOnclickListener = value
        }

    var onFavIconClickListener: ((View: View) -> Unit)? = null
    var onBackIconClickListener: ((View: View) -> Unit)? = null

    val publisher: PublishRelay<String>
        get() = binding.searchField.publisher

    fun setRightButtonVisibility(visible: Boolean) {
        binding.rlRight.isVisible = visible
        binding.searchTitlePart.isVisible = visible
        binding.searchField.isVisible = !visible
    }

    fun setLeftButtonVisibility(visible: Boolean) {
        binding.ivSearchIcon.isVisible = !visible
        binding.ivFavIcon.isVisible = visible
        binding.searchTitlePart.isVisible = visible
        binding.searchField.isVisible = !visible
        binding.rlLeft.isVisible = visible
        binding.rlRight.isVisible = visible
        binding.ivSearchIcon.isVisible = !visible
        binding.ivFavIcon.isVisible = visible
        binding.ivBack.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_baseline_arrow_back_24
            )
        )
    }

    fun setFavIcon(isSelected: Boolean) {
        if (isSelected) {
            binding.ivFavIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_baseline_star_24
                )
            )
        } else {
            binding.ivFavIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_baseline_star_outline_24
                )
            )
        }
    }

    /*************************************************************
     * INIT
     *************************************************************************/
    init {
        initAttrs(attrs)
        binding.ivSearchIcon.setOnClickListener {
            setRightButtonVisibility(false)
        }
        binding.ivFavIcon.setOnClickListener {
            onFavIconClickListener?.invoke(it)
        }

        binding.ivBack.setOnClickListener {
            onBackIconClickListener?.invoke(it)
        }
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                attrs, R.styleable.toolbar_layout, 0, 0
            )
            try {
                title = typedArray.getString(R.styleable.toolbar_layout_tl_title)
                hasSearchField =
                    typedArray.getBoolean(
                        R.styleable.toolbar_layout_tl_hasSearchField,
                        _hasSearchField
                    )
                hint = typedArray.getString(R.styleable.toolbar_layout_tl_search_hint)
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun showKeyboard() {
        binding.searchField.showKeyboard()
    }

    fun hidekeyboard() {
        binding.searchField.hideKeyboard()
    }

    fun registerToolbarToActivity(activity: AppCompatActivity?, backPressListener: () -> Unit) {
        activity?.let {
            it.setSupportActionBar(binding.dashToolbar)
            it.supportActionBar?.setDisplayShowTitleEnabled(false)
            it.supportActionBar?.setDisplayShowCustomEnabled(true)
            binding.ivBack.setOnClickListener {
                backPressListener.invoke()
            }
        }
    }
}