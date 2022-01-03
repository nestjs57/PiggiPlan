package com.arnoract.piggiplan.ui.restaurant.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant
import com.arnoract.piggiplan.ui.restaurant.model.UiRestaurant

object RestaurantToUiRestaurantMapper : Mapper<Restaurant?, UiRestaurant> {
    override fun map(from: Restaurant?): UiRestaurant {
        return UiRestaurant(
            id = from?.id ?: 0,
            photo = from?.photo ?: "",
            name = from?.name ?: "",
            isSelected = false
        )
    }
}