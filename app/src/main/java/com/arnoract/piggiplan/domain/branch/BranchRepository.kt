package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.domain.model.branch.Branch

interface BranchRepository {
    suspend fun setBranches(branches: List<Branch>)
    suspend fun getBranchesByRestaurantId(restaurantId: RestaurantId): List<Branch>
    suspend fun getBranchByBranchId(branchId: BranchId): Branch
}