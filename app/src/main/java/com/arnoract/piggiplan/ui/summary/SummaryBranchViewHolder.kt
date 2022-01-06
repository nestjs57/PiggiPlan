package com.arnoract.piggiplan.ui.summary

import android.view.ViewGroup
import com.arnoract.piggiplan.base.inflater
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.databinding.ViewItemSummaryBranchBinding
import com.arnoract.piggiplan.ui.summary.model.UiSummaryBranch
import com.bumptech.glide.Glide

class SummaryBranchViewHolder(
    private val binding: ViewItemSummaryBranchBinding,
    private val mListener: OnBranchItemClickListener
) : ItemViewHolder<UiSummaryBranch>(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: OnBranchItemClickListener,
        ): ItemViewHolder<UiSummaryBranch> {
            return SummaryBranchViewHolder(
                ViewItemSummaryBranchBinding.inflate(
                    parent.inflater(),
                    parent,
                    false
                ), listener
            )
        }
    }

    private var mData: UiSummaryBranch? = null

    init {
        binding.branchItemLayout.setOnClickListener {
            mData?.let {
                mListener.onBranchItemClick(it.branchId)
            }
        }
    }

    override fun fillData(data: UiSummaryBranch?, position: Int) {
        mData = data
        data?.let {
            fillBranchName(it)
            fillDescription(it)
            fillImage(it)
        }
    }

    private fun fillBranchName(it: UiSummaryBranch) {
        binding.branchNameTextView.text = it.branchName
    }

    private fun fillDescription(it: UiSummaryBranch) {
        binding.branchDescriptionTextView.text = it.description
    }

    private fun fillImage(data: UiSummaryBranch) {
        binding.photoImageView.apply {
            Glide
                .with(this.context)
                .load(data.photoImage)
                .centerCrop()
                .into(this)
        }
    }

    interface OnBranchItemClickListener {
        fun onBranchItemClick(branchId: BranchId)
    }
}