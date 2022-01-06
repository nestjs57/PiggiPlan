package com.arnoract.piggiplan.core.db.model.history

import androidx.room.Embedded
import androidx.room.Relation

data class HistoryWithFriendsHistory(
    @Embedded val history: HistoryEntity,
    @Relation(
        parentColumn = "history_id",
        entityColumn = "historyId"
    )
    val friendsHistory: List<FriendHistoryEntity>
)
