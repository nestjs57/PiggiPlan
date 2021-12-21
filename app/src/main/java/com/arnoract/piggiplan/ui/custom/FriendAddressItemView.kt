package com.arnoract.piggiplan.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.arnoract.piggiplan.databinding.ViewItemFriendAddressBinding
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.arnoract.piggiplan.view.AbstractCustomView

class FriendAddressItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AbstractCustomView<UiFriendAddress>(context, attrs, defStyleAttr) {

    private var mListener: OnFriendAddressItemListener? = null
    private lateinit var binding: ViewItemFriendAddressBinding

    override fun inflateLayout(inflater: LayoutInflater) {
        binding = ViewItemFriendAddressBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    override fun setUpView() {
        binding.friendAddressItemLayout.setOnClickListener {
            data?.let {
                mListener?.onFriendAddressClick(it.id)
            }
        }
    }

    override fun fillDataNonNull(d: UiFriendAddress) {
        this.tag = data?.id
        binding.nameTextView.text = data?.name
        binding.addressTextView.text = data?.addressName
    }


    fun setOnFriendAddressItemListener(listener: OnFriendAddressItemListener) {
        this.mListener = listener
    }

    interface OnFriendAddressItemListener {
        fun onFriendAddressClick(
            id: Long
        )
    }
}