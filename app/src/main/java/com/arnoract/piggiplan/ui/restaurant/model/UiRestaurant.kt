package com.arnoract.piggiplan.ui.restaurant.model

import androidx.recyclerview.widget.DiffUtil
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

data class UiRestaurant(
    val id: RestaurantId,
    val photo: String,
    val name: String,
    val isSelected: Boolean
)

class UiRestaurantDiffCallback : DiffUtil.ItemCallback<UiRestaurant>() {
    override fun areItemsTheSame(
        oldItem: UiRestaurant,
        newItem: UiRestaurant,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UiRestaurant,
        newItem: UiRestaurant,
    ): Boolean {
        return oldItem == newItem
    }
}