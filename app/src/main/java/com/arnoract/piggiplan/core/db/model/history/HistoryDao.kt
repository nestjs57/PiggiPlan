package com.arnoract.piggiplan.core.db.model.history

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.arnoract.piggiplan.core.db.model.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HistoryDao : BaseDao<HistoryEntity>() {
    @Transaction
    @Query("SELECT * FROM history WHERE history_id = :historyId")
    abstract suspend fun getHistoryWithFriends(historyId: HistoryId): HistoryWithFriendsHistory

    @Query("SELECT * from history")
    abstract fun observeHistories(): Flow<List<HistoryEntity>>
}