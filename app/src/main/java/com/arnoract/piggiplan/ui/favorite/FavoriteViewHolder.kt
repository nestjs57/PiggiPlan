package com.arnoract.piggiplan.ui.favorite

import android.view.ViewGroup
import com.arnoract.piggiplan.R
import com.arnoract.piggiplan.base.inflater
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.databinding.ViewItemBranchNearByBinding
import com.arnoract.piggiplan.ui.favorite.model.UiFavorite
import com.bumptech.glide.Glide

class FavoriteViewHolder(
    private val binding: ViewItemBranchNearByBinding,
    private val mListener: OnBranchItemClickListener
) : ItemViewHolder<UiFavorite>(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: OnBranchItemClickListener,
        ): ItemViewHolder<UiFavorite> {
            return FavoriteViewHolder(
                ViewItemBranchNearByBinding.inflate(
                    parent.inflater(),
                    parent,
                    false
                ), listener
            )
        }
    }

    private var mData: UiFavorite? = null

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

    override fun fillData(data: UiFavorite?, position: Int) {
        mData = data
        data?.let {
            fillBranchName(it)
            fillDescription(it)
            fillImage(it)
            fillFavorite(it)
        }
    }

    private fun fillFavorite(it: UiFavorite) {
        val drawable = if (it.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        binding.favoriteImageButton.setImageResource(drawable)
    }

    private fun fillBranchName(it: UiFavorite) {
        binding.branchNameTextView.text = it.branchName
    }

    private fun fillDescription(it: UiFavorite) {
        binding.branchDescriptionTextview.text = it.description
    }

    private fun fillImage(data: UiFavorite) {
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