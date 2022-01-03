package com.arnoract.piggiplan.core.db.model.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_history")
data class FriendHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "friend_history_id")
    val friendHistoryId: FriendHistoryId = 0,
    @ColumnInfo(name = "historyId")
    val historyId: HistoryId,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "address_id")
    val addressId: String,
    @ColumnInfo(name = "address_name")
    val addressName: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
)

typealias FriendHistoryId = Long