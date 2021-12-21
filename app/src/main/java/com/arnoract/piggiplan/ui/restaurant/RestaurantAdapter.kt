package com.arnoract.piggiplan.ui.restaurant

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.ui.restaurant.model.UiRestaurant
import com.arnoract.piggiplan.ui.restaurant.model.UiRestaurantDiffCallback

class RestaurantAdapter(private val onRestaurantItemClickListener: RestaurantViewHolder.OnRestaurantItemClickListener) :
    ListAdapter<UiRestaurant, ItemViewHolder<UiRestaurant>>(
        UiRestaurantDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<UiRestaurant> {
        return RestaurantViewHolder.create(parent, onRestaurantItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<UiRestaurant>, position: Int) {
        holder.fillData(getItem(position), position)
    }
}