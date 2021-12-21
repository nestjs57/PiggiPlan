package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantDao
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant
import com.arnoract.piggiplan.domain.model.restaurant.mapper.RestaurantToRestaurantEntityMapper

class RestaurantRepositoryImpl(
    private val restaurantDao: RestaurantDao,
) : RestaurantRepository {
    override suspend fun addRestaurant(restaurants: List<Restaurant>) {
        restaurantDao.insertOrUpdate(restaurants.map {
            RestaurantToRestaurantEntityMapper.map(it)
        })
    }
}