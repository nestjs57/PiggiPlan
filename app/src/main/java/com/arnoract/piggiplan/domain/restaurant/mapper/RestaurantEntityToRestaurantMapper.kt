package com.arnoract.piggiplan.domain.restaurant.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantEntity
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant
import com.arnoract.piggiplan.domain.restaurant.exception.RestaurantTypeNotFoundException

object RestaurantEntityToRestaurantMapper : Mapper<RestaurantEntity, Restaurant> {
    override fun map(from: RestaurantEntity): Restaurant {
        return Restaurant(
            id = from.id,
            photo = from.photo,
            name = from.name,
            nameEnglish = from.nameEnglish,
            restaurantType = mapType(from.restaurantType) ?: throw RestaurantTypeNotFoundException()
        )
    }

    private fun mapType(type: Int): Restaurant.Type? {
        return when (type) {
            1 -> Restaurant.Type.THAI_BBQ
            2 -> Restaurant.Type.SHABU
            3 -> Restaurant.Type.CAFE
            4 -> Restaurant.Type.FOOD
            5 -> Restaurant.Type.ICE_CREAM
            else -> null
        }
    }
}