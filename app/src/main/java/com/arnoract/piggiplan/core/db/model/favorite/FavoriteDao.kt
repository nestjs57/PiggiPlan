package com.arnoract.piggiplan.core.db.model.favorite

import androidx.room.Dao
import androidx.room.Query
import com.arnoract.piggiplan.core.db.model.BaseDao
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoriteDao : BaseDao<FavoriteEntity>() {
    @Query("SELECT * from favorite")
    abstract fun observeFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT * from favorite")
    abstract suspend fun findAll(): List<FavoriteEntity>

    @Query("DELETE FROM favorite WHERE branch_id = :branchId")
    abstract suspend fun deleteByBranchId(branchId: BranchId)
}