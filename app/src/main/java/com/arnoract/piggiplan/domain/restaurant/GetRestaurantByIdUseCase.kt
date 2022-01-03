package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant

class GetRestaurantByIdUseCase(
    private val restaurantRepository: RestaurantRepository
) : UseCase<RestaurantId, Restaurant>() {
    override suspend fun execute(parameters: RestaurantId): Restaurant {
        return restaurantRepository.getRestaurantById(parameters)
    }
}