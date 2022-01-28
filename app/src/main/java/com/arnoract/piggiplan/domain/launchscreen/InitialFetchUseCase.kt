package com.arnoract.piggiplan.domain.launchscreen

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.domain.branch.BranchRepository
import com.arnoract.piggiplan.domain.restaurant.RestaurantRepository

class InitialFetchUseCase(
    private val restaurantRepository: RestaurantRepository,
    private val branchRepository: BranchRepository
) : UseCase<Unit, Unit>() {
    override suspend fun execute(parameters: Unit) {
        branchRepository.fetchBranches()
        restaurantRepository.fetchRestaurants()
    }
}