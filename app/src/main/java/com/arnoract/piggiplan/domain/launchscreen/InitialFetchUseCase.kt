package com.arnoract.piggiplan.domain.launchscreen

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.domain.branch.BranchRepository
import com.arnoract.piggiplan.domain.restaurant.RestaurantRepository
import com.arnoract.piggiplan.test.model.BranchTestData
import com.arnoract.piggiplan.test.model.RestaurantTestData

class InitialFetchUseCase(
    private val restaurantRepository: RestaurantRepository,
    private val branchRepository: BranchRepository
) : UseCase<Unit, Unit>() {
    override suspend fun execute(parameters: Unit) {
        val restaurants = RestaurantTestData.all()
        val branches = BranchTestData.all()
        restaurantRepository.setRestaurants(restaurants)
        branchRepository.setBranches(branches)
    }
}