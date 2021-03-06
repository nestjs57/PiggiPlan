package com.arnoract.piggiplan.core.db

import com.arnoract.piggiplan.core.db.model.branch.BranchDao
import com.arnoract.piggiplan.core.db.model.favorite.FavoriteDao
import com.arnoract.piggiplan.core.db.model.history.FriendHistoryDao
import com.arnoract.piggiplan.core.db.model.history.HistoryDao
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantDao

class DaoBuilder(
    private val roomDatabaseStorage: RoomDatabaseStorage,
) {
    fun getRestaurantDao(): RestaurantDao = roomDatabaseStorage.restaurantDao()
    fun getBranchDao(): BranchDao = roomDatabaseStorage.branchDao()
    fun getFavoriteDao(): FavoriteDao = roomDatabaseStorage.FavoriteDao()
    fun getHistoryDao(): HistoryDao = roomDatabaseStorage.HistoryDao()
    fun getFriendHistoryDao() : FriendHistoryDao = roomDatabaseStorage.FriendHistoryDao()
}