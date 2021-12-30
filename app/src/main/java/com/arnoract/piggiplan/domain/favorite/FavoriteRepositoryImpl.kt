package com.arnoract.piggiplan.domain.favorite

import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.db.model.favorite.FavoriteDao
import com.arnoract.piggiplan.core.db.model.favorite.FavoriteEntity
import com.arnoract.piggiplan.domain.model.favorite.Favorite
import com.arnoract.piggiplan.domain.model.favorite.mapper.FavoriteEntityToFavoriteMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun getFavorites(): Flow<List<Favorite>> {
        return favoriteDao.observeFavorite().map { favorite ->
            favorite.map {
                FavoriteEntityToFavoriteMapper.map(it)
            }
        }
    }

    override suspend fun updateFavorite(branchId: BranchId) {
        if (favoriteDao.findAll().none { it.branchId == branchId }) {
            favoriteDao.insert(FavoriteEntity(branchId = branchId))
        } else {
            favoriteDao.deleteByBranchId(branchId = branchId)
        }
    }

    override suspend fun getIsFavorite(branchId: BranchId): Boolean {
        return favoriteDao.findAll().any { it.branchId == branchId }
    }
}