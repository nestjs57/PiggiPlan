package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.core.db.model.branch.BranchDao

class BranchRepositoryImpl(
    private val branchDao: BranchDao
) : BranchRepository {

}