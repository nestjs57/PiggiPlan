package com.arnoract.piggiplan.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.core.dimenToPx
import com.arnoract.piggiplan.databinding.ViewItemHorizontalLineBinding

class LineItemHorizontalView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewItemHorizontalLineBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setMargin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        binding.divider.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            context.dimenToPx(R.dimen.divider_height)
        ).apply {
            setMargins(left, top, right, bottom)
        }
    }
}