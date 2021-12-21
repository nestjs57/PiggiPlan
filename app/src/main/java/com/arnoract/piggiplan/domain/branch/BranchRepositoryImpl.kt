package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.core.db.model.branch.BranchDao
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.domain.model.branch.mapper.BranchToBranchEntityMapper

class BranchRepositoryImpl(
    private val branchDao: BranchDao
) : BranchRepository {
    override suspend fun setBranches(branches: List<Branch>) {
        branchDao.insertOrUpdate(branches.map {
            BranchToBranchEntityMapper.map(it)
        })
    }
}