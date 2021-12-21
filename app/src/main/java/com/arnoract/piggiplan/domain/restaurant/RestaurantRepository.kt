package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.domain.model.restaurant.Restaurant

interface RestaurantRepository {
    suspend fun addRestaurant(restaurants : List<Restaurant>)
}