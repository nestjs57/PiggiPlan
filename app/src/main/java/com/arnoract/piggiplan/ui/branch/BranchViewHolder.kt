package com.arnoract.piggiplan.ui.branch

import android.view.ViewGroup
import com.arnoract.piggiplan.base.inflater
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.databinding.ViewItemBranchBinding
import com.arnoract.piggiplan.ui.branch.model.UiBranch
import com.bumptech.glide.Glide

class BranchViewHolder(
    private val binding: ViewItemBranchBinding,
    private val mListener: OnBranchItemClickListener
) : ItemViewHolder<UiBranch>(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: OnBranchItemClickListener,
        ): ItemViewHolder<UiBranch> {
            return BranchViewHolder(
                ViewItemBranchBinding.inflate(
                    parent.inflater(),
                    parent,
                    false
                ), listener
            )
        }
    }

    private var mData: UiBranch? = null

    init {
        binding.root.setOnClickListener {
            mData?.let {
                mListener.onBranchItemClick(it.id)
            }
        }
    }

    override fun fillData(data: UiBranch?, position: Int) {
        mData = data
        data?.let {
            fillImage(it)
            fillBranchName(it)
            fillDescription(it)
        }
    }

    private fun fillImage(data: UiBranch) {
        binding.photoImageView.apply {
            Glide
                .with(this.context)
                .load(data.photoImage)
                .centerCrop()
                .into(this)
        }
    }

    private fun fillBranchName(it: UiBranch) {
        binding.branchNameTextView.text = it.branchName
    }

    private fun fillDescription(it: UiBranch) {
        binding.branchDescriptionTextview.text = it.description
    }

    interface OnBranchItemClickListener {
        fun onBranchItemClick(id: BranchId)
    }
}