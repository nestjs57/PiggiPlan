package com.arnoract.piggiplan.core.api.restaurant.model

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant
import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
    @field:SerializedName("id")
    val id: RestaurantId,
    @field:SerializedName("photo")
    val photo: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("nameEnglish")
    val nameEnglish: String,
    @field:SerializedName("restaurantType")
    val restaurantType: Restaurant.Type
)
