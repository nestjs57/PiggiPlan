package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.domain.model.branch.Branch

interface BranchRepository {
    suspend fun setBranches(branches: List<Branch>)
}