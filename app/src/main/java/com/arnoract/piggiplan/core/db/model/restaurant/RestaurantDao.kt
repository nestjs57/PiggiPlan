package com.arnoract.piggiplan.core.db.model.restaurant

import androidx.room.Dao
import androidx.room.Query
import com.arnoract.piggiplan.core.db.model.BaseDao

@Dao
abstract class RestaurantDao : BaseDao<RestaurantEntity>() {
    @Query("SELECT * from restaurant")
    abstract suspend fun findAll(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurant WHERE res_id = :restaurantId")
    abstract suspend fun findRestaurantById(restaurantId: RestaurantId): RestaurantEntity
}