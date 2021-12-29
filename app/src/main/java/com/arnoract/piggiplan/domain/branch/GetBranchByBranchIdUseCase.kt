package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.domain.model.branch.Branch

class GetBranchByBranchIdUseCase(
    private val branchRepository: BranchRepository
) : UseCase<BranchId, Branch>() {
    override suspend fun execute(parameters: BranchId): Branch {
        return branchRepository.getBranchByBranchId(parameters)
    }
}