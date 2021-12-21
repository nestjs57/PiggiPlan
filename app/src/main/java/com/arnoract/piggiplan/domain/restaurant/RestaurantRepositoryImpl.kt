package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantDao

class RestaurantRepositoryImpl(
    private val restaurantDao: RestaurantDao,
) : RestaurantRepository {

}