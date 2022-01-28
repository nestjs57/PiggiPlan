package com.arnoract.piggiplan.domain.favorite

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.core.db.model.branch.BranchId

class UpdateFavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) : UseCase<BranchId, Unit>() {
    override suspend fun execute(parameters: BranchId) {
        favoriteRepository.updateFavorite(parameters)
    }
}