package com.arnoract.piggiplan.core.db.model.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arnoract.piggiplan.core.db.model.branch.BranchId

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_id")
    val favoriteId: FavoriteId = 0,
    @ColumnInfo(name = "branch_id")
    val branchId: BranchId,
)

typealias FavoriteId = Long
