package com.arnoract.piggiplan.domain.restaurant

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.domain.model.restaurant.Restaurant

class GetRestaurantsUseCase(
    private val restaurantRepository: RestaurantRepository
) : UseCase<Unit, List<Restaurant>>() {
    override suspend fun execute(parameters: Unit): List<Restaurant> {
        return restaurantRepository.getRestaurants()
    }
}