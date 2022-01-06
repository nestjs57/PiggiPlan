package com.arnoract.piggiplan.domain.history

import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.domain.model.history.History
import com.arnoract.piggiplan.domain.model.history.SaveSearchBranchNearbyHistoryForm
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyHistory
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun saveSearchBranchNearbyHistory(formSearchBranchNearby: SaveSearchBranchNearbyHistoryForm)
    suspend fun getSearchBranchNearbyHistory(historyId: HistoryId): SearchBranchNearbyHistory
    fun observeHistories(): Flow<List<History>>
}