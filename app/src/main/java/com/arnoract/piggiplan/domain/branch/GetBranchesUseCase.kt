package com.arnoract.piggiplan.domain.branch

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.domain.model.branch.Branch

class GetBranchesUseCase(
    private val branchRepository: BranchRepository
) : UseCase<Unit, List<Branch>>() {
    override suspend fun execute(parameters: Unit): List<Branch> {
        return branchRepository.getBranches()
    }
}