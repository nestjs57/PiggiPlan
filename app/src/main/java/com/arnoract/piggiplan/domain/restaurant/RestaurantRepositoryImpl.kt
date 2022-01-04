package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.core.api.restaurant.RestaurantApi
import com.arnoract.piggiplan.core.api.restaurant.model.RestaurantResponseToRestaurantEntityMapper
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantDao
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.core.successOrThrow
import com.arnoract.piggiplan.core.unSafeToResult
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant
import com.arnoract.piggiplan.domain.model.restaurant.mapper.RestaurantToRestaurantEntityMapper
import com.arnoract.piggiplan.domain.restaurant.mapper.RestaurantEntityToRestaurantMapper

class RestaurantRepositoryImpl(
    private val restaurantDao: RestaurantDao,
    private val restaurantApi: RestaurantApi
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

    override suspend fun getRestaurantById(restaurantId: RestaurantId): Restaurant {
        return RestaurantEntityToRestaurantMapper.map(restaurantDao.findRestaurantById(restaurantId))
    }

    override suspend fun fetchRestaurants() {
        val restaurants = restaurantApi.fetchAllRestaurants().unSafeToResult().successOrThrow()
        restaurantDao.insertOrUpdate(restaurants.map {
            RestaurantResponseToRestaurantEntityMapper.map(it)
        })
    }
}