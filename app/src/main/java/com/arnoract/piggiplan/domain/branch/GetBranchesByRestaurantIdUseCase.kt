package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.domain.model.branch.Branch

class GetBranchesByRestaurantIdUseCase(
    private val branchRepository: BranchRepository
) : UseCase<RestaurantId, List<Branch>>() {
    override suspend fun execute(parameters: RestaurantId): List<Branch> {
        return branchRepository.getBranchesByRestaurantId(parameters)
    }
}