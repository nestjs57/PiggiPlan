package com.arnoract.piggiplan.core.db.model.history

import androidx.room.Dao
import androidx.room.Query
import com.arnoract.piggiplan.core.db.model.BaseDao

@Dao
abstract class FriendHistoryDao : BaseDao<FriendHistoryEntity>() {
    @Query("DELETE FROM friend_history WHERE historyId = :historyId")
    abstract suspend fun deleteByHistoryId(historyId: HistoryId)
}