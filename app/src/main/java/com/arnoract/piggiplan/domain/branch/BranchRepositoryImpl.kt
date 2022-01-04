package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.core.api.branch.BranchApi
import com.arnoract.piggiplan.core.api.branch.model.BranchResponseToBranchEntityMapper
import com.arnoract.piggiplan.core.db.model.branch.BranchDao
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.core.successOrThrow
import com.arnoract.piggiplan.core.unSafeToResult
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.domain.model.branch.mapper.BranchEntityToBranchMapper
import com.arnoract.piggiplan.domain.model.branch.mapper.BranchToBranchEntityMapper

class BranchRepositoryImpl(
    private val branchDao: BranchDao,
    private val branchApi: BranchApi
) : BranchRepository {
    override suspend fun setBranches(branches: List<Branch>) {
        branchDao.insertOrUpdate(branches.map {
            BranchToBranchEntityMapper.map(it)
        })
    }

    override suspend fun getBranchesByRestaurantId(restaurantId: RestaurantId): List<Branch> {
        return branchDao.findByRestaurantId(restaurantId).map {
            BranchEntityToBranchMapper.map(it)
        }
    }

    override suspend fun getBranchByBranchId(branchId: BranchId): Branch {
        return BranchEntityToBranchMapper.map(branchDao.findByBranchId(branchId))
    }

    override suspend fun getBranches(): List<Branch> {
        return branchDao.findAll().map {
            BranchEntityToBranchMapper.map(it)
        }
    }

    override suspend fun fetchBranches() {
        val branches = branchApi.fetchAllBranches().unSafeToResult().successOrThrow()
        branchDao.insertOrUpdate(branches.map {
            BranchResponseToBranchEntityMapper.map(it)
        })
    }
}