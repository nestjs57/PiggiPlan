package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant

interface RestaurantRepository {
    suspend fun setRestaurants(restaurants: List<Restaurant>)
    suspend fun getRestaurants(): List<Restaurant>
    suspend fun getRestaurantById(restaurantId: RestaurantId): Restaurant
}