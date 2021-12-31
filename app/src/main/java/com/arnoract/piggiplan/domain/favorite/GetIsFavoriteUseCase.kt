package com.arnoract.piggiplan.domain.favorite

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.core.db.model.branch.BranchId

class GetIsFavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) : UseCase<BranchId, Boolean>() {
    override suspend fun execute(parameters: BranchId): Boolean {
        return favoriteRepository.getIsFavorite(parameters)
    }
}