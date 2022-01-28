package com.arnoract.piggiplan.domain.favorite

import com.arnoract.piggiplan.core.UseCase

class DeleteAllFavoritesUseCase(
    private val favoriteRepository: FavoriteRepository
) : UseCase<Unit, Unit>() {
    override suspend fun execute(parameters: Unit) {
        favoriteRepository.clear()
    }
}