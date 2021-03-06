package com.arnoract.piggiplan.ui.branch

import android.view.ViewGroup
import com.arnoract.piggiplan.R
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

    init {
        binding.branchItemLayout.setOnClickListener {
            mData?.let {
                mListener.onBranchItemClick(it.id)
            }
        }
        binding.favoriteImageButton.setOnClickListener {
            mData?.let {
                mListener.onFavoriteClick(it.id)
            }
        }
    }

    override fun fillData(data: UiBranchNearby?, position: Int) {
        mData = data
        data?.let {
            fillBranchName(it)
            fillDescription(it)
            fillImage(it)
            fillFavorite(it)
        }
    }

    private fun fillFavorite(it: UiBranchNearby) {
        val drawable = if (it.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        binding.favoriteImageButton.setImageResource(drawable)
    }

    private fun fillBranchName(it: UiBranchNearby) {
        binding.branchNameTextView.text = it.branchName
    }

    private fun fillDescription(it: UiBranchNearby) {
        binding.branchDescriptionTextview.text = it.description
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
        fun onFavoriteClick(branchId: BranchId)
    }
}