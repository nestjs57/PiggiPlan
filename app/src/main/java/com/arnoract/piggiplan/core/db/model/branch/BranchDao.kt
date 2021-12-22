package com.arnoract.piggiplan.core.db.model.branch

import androidx.room.Dao
import androidx.room.Query
import com.arnoract.piggiplan.core.db.model.BaseDao
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

@Dao
abstract class BranchDao : BaseDao<BranchEntity>() {
    @Query("SELECT * from branch")
    abstract suspend fun findAll(): List<BranchEntity>

    @Query("SELECT * FROM branch WHERE res_id = :restaurantId")
    abstract suspend fun findByRestaurantId(restaurantId: RestaurantId): List<BranchEntity>
}