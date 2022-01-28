package com.arnoract.piggiplan.domain.favorite

import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.domain.model.favorite.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavorites(): Flow<List<Favorite>>
    suspend fun updateFavorite(branchId: BranchId)
    suspend fun getIsFavorite(branchId: BranchId): Boolean
    suspend fun clear()
}