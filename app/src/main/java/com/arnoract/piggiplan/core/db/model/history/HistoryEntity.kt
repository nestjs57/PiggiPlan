package com.arnoract.piggiplan.core.db.model.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "history_id")
    val historyId: HistoryId,
    @ColumnInfo(name = "res_id")
    val resId: RestaurantId,
)

typealias HistoryId = String

