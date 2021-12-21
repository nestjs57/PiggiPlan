package com.arnoract.piggiplan.core.db.model.branch

import androidx.room.Dao
import androidx.room.Query
import com.arnoract.piggiplan.core.db.model.BaseDao

@Dao
abstract class BranchDao : BaseDao<BranchEntity>() {
    @Query("SELECT * from branch")
    abstract suspend fun findAll(): List<BranchEntity>
}