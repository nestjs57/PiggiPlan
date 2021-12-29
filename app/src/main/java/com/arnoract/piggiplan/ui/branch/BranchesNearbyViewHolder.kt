package com.arnoract.piggiplan.ui.branch

import android.view.ViewGroup
import com.arnoract.piggiplan.base.inflater
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.databinding.ViewItemBranchNearByBinding
import com.arnoract.piggiplan.ui.branch.model.UiBranchNearby
import com.bumptech.glide.Glide

class BranchesNearbyViewHolder(
    private val binding: ViewItemBranchNearByBinding,
    private val mListener: OnBranchItemClickListener
) : ItemViewHolder<UiBranchNearby>(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: OnBranchItemClickListener,
        ): ItemViewHolder<UiBranchNearby> {
            return BranchesNearbyViewHolder(
                ViewItemBranchNearByBinding.inflate(
                    parent.inflater(),
                    parent,
                    false
                ), listener
            )
        }
    }

    private var mData: UiBranchNearby? = null

    override fun fillData(data: UiBranchNearby?, position: Int) {
        mData = data
        data?.let {
            fillBranchName(it)
            fillDescription(it)
            fillImage(it)
        }
    }

    private fun fillBranchName(it: UiBranchNearby) {
        binding.branchNameTextView.text = it.branchName
    }

    private fun fillDescription(it: UiBranchNearby) {
        binding.branchDescription.text = it.description
    }

    private fun fillImage(data: UiBranchNearby) {
        binding.photoImageView.apply {
            Glide
                .with(this.context)
                .load(data.photoImage)
                .centerCrop()
                .into(this)
        }
    }

    interface OnBranchItemClickListener {
        fun onBranchItemClick(id: BranchId)
    }

}