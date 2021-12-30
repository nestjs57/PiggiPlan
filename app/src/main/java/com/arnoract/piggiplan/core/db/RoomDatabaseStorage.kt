package com.arnoract.piggiplan.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arnoract.piggiplan.core.db.model.branch.BranchDao
import com.arnoract.piggiplan.core.db.model.branch.BranchEntity
import com.arnoract.piggiplan.core.db.model.favorite.FavoriteDao
import com.arnoract.piggiplan.core.db.model.favorite.FavoriteEntity
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantDao
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantEntity

@Database(
    entities = [RestaurantEntity::class, BranchEntity::class, FavoriteEntity::class],
    version = 1
)
@TypeConverters(DateConverter::class, LongListConverter::class)
abstract class RoomDatabaseStorage : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
    abstract fun branchDao(): BranchDao
    abstract fun FavoriteDao(): FavoriteDao
}