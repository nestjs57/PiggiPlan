package com.arnoract.piggiplan.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.arnoract.piggiplan.databinding.ViewItemFriendAddressBinding
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress

class FriendAddressItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewItemFriendAddressBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun fillData(data: UiFriendAddress) {
        this.tag = data.id
        binding.nameTextView.text = data.name
        binding.addressTextView.text = data.addressName
    }
}