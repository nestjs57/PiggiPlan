package com.arnoract.piggiplan.domain.history

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyHistory

class GetSearchBranchNearbyHistoryUseCase(
    private val historyRepository: HistoryRepository
) : UseCase<HistoryId, SearchBranchNearbyHistory>() {
    override suspend fun execute(parameters: HistoryId): SearchBranchNearbyHistory {
        return historyRepository.getSearchBranchNearbyHistory(parameters)
    }
}