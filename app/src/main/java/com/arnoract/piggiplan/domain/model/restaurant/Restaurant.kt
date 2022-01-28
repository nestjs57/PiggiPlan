package com.arnoract.piggiplan.domain.model.restaurant

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

data class Restaurant(
    val id: RestaurantId,
    val photo: String,
    val name: String,
    val nameEnglish: String,
    val restaurantType: Type
) {
    enum class Type {
        THAI_BBQ,
        SHABU,
        CAFE,
        FOOD,
        ICE_CREAM,
    }
}


