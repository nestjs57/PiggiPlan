package com.arnoract.piggiplan.domain.model.restaurant.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantEntity
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant

object RestaurantToRestaurantEntityMapper : Mapper<Restaurant, RestaurantEntity> {
    override fun map(from: Restaurant): RestaurantEntity {
        return RestaurantEntity(
            id = from.id,
            photo = from.photo,
            name = from.name,
            nameEnglish = from.nameEnglish,
            restaurantType = mapRestaurantType(from.restaurantType)
        )
    }

    private fun mapRestaurantType(type: Restaurant.Type): Int {
        return when (type) {
            Restaurant.Type.THAI_BBQ -> 1
            Restaurant.Type.SHABU -> 2
            Restaurant.Type.CAFE -> 3
            Restaurant.Type.FOOD -> 4
            Restaurant.Type.ICE_CREAM -> 5
        }
    }
}