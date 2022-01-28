package com.arnoract.piggiplan.ui.restaurant

import android.view.View
import android.view.ViewGroup
import com.arnoract.piggiplan.base.inflater
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.databinding.ViewItemRestaurantBinding
import com.arnoract.piggiplan.ui.restaurant.model.UiRestaurant
import com.bumptech.glide.Glide

class RestaurantViewHolder(
    private val binding: ViewItemRestaurantBinding,
    private val mListener: OnRestaurantItemClickListener
) : ItemViewHolder<UiRestaurant>(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
            listener: OnRestaurantItemClickListener,
        ): ItemViewHolder<UiRestaurant> {
            return RestaurantViewHolder(
                ViewItemRestaurantBinding.inflate(
                    parent.inflater(),
                    parent,
                    false
                ), listener
            )
        }
    }

    private var mData: UiRestaurant? = null

    init {
        binding.restaurantCardView.setOnClickListener {
            mData?.let {
                mListener.onRestaurantItemClick(it.id)
            }
        }
    }

    override fun fillData(data: UiRestaurant?, position: Int) {
        data?.let {
            mData = it
            fillImage(it)
            fillSelected(it)
        }
    }

    private fun fillSelected(data: UiRestaurant) {
        binding.selectedImageView.visibility = if (data.isSelected) View.VISIBLE else View.GONE
    }

    private fun fillImage(data: UiRestaurant) {
        binding.photoImageView.apply {
            Glide
                .with(this.context)
                .load(data.photo)
                .centerCrop()
                .into(this)
        }
    }

    interface OnRestaurantItemClickListener {
        fun onRestaurantItemClick(id: RestaurantId)
    }

}