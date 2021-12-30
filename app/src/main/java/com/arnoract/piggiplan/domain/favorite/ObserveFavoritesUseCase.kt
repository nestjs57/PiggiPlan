package com.arnoract.piggiplan.domain.favorite

import com.arnoract.piggiplan.core.MediatorUseCase
import com.arnoract.piggiplan.core.Result
import com.arnoract.piggiplan.domain.model.favorite.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveFavoritesUseCase(
    private val favoriteRepository: FavoriteRepository
) : MediatorUseCase<Unit, List<Favorite>>() {
    override fun execute(parameters: Unit): Flow<Result<List<Favorite>>> {
        return favoriteRepository.getFavorites().map { Result.Success(it) }
    }
}