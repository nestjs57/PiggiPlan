package com.arnoract.piggiplan.ui.branch

import android.view.ViewGroup
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.inflater
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.databinding.ViewItemBranchDetailNearbyBinding
import com.arnoract.piggiplan.ui.branch.model.UiBranchDetailNearby
import com.arnoract.piggiplan.util.FormatUtils
import com.bumptech.glide.Glide

class BranchDetailNearbyViewHolder(
    private val binding: ViewItemBranchDetailNearbyBinding,
    private val mListener: OnBranchItemClickListener
) : ItemViewHolder<UiBranchDetailNearby>(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: OnBranchItemClickListener,
        ): ItemViewHolder<UiBranchDetailNearby> {
            return BranchDetailNearbyViewHolder(
                ViewItemBranchDetailNearbyBinding.inflate(
                    parent.inflater(),
                    parent,
                    false
                ), listener
            )
        }
    }

    private var mData: UiBranchDetailNearby? = null

    init {
        binding.root.setOnClickListener {
            mData?.let {
                mListener.onBranchItemClick(it.id)
            }
        }
    }

    override fun fillData(data: UiBranchDetailNearby?, position: Int) {
        mData = data
        data?.let {
            fillImage(it)
            fillBranchName(it)
            fillDescription(it)
            fillDistance(it)
        }
    }

    private fun fillImage(data: UiBranchDetailNearby) {
        binding.photoImageView.apply {
            Glide
                .with(this.context)
                .load(data.photoImage)
                .centerCrop()
                .into(this)
        }
    }

    private fun fillBranchName(it: UiBranchDetailNearby) {
        binding.branchNameTextView.text = it.branchName
    }

    private fun fillDescription(it: UiBranchDetailNearby) {
        binding.branchDescriptionTextview.text = it.description
    }

    private fun fillDistance(it: UiBranchDetailNearby) {
        val distance = if (it.distance < 1000) {
            context.getString(
                R.string.branch_detail_nearby_away_from_branch_m,
                it.distance.toInt().toString()
            )
        } else {
            context.getString(
                R.string.branch_detail_nearby_away_from_branch_km,
                FormatUtils.formatOptionalTwoDecimalPlaces(it.distance.div(1000).toDouble())
            )
        }
        binding.distanceTextview.text = distance
    }

    interface OnBranchItemClickListener {
        fun onBranchItemClick(id: BranchId)
    }
}