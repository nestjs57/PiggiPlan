package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantDao
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant
import com.arnoract.piggiplan.domain.model.restaurant.mapper.RestaurantToRestaurantEntityMapper
import com.arnoract.piggiplan.domain.restaurant.mapper.RestaurantEntityToRestaurantMapper

class RestaurantRepositoryImpl(
    private val restaurantDao: RestaurantDao,
) : RestaurantRepository {
    override suspend fun setRestaurants(restaurants: List<Restaurant>) {
        restaurantDao.insertOrUpdate(restaurants.map {
            RestaurantToRestaurantEntityMapper.map(it)
        })
    }

    override suspend fun getRestaurants(): List<Restaurant> {
        return restaurantDao.findAll().map {
            RestaurantEntityToRestaurantMapper.map(it)
        }
    }
}