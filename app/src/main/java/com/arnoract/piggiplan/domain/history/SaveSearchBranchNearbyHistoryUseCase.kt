package com.arnoract.piggiplan.domain.history

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyFriendHistory
import com.arnoract.piggiplan.domain.model.history.mapper.SaveSearchBranchNearbyHistoryToSaveSearchBranchNearbyHistoryFormMapper

class SaveSearchBranchNearbyHistoryUseCase(
    private val historyRepository: HistoryRepository
) : UseCase<SaveSearchBranchNearbyHistoryUseCase.Params, Unit>() {
    override suspend fun execute(parameters: Params) {
        historyRepository.saveSearchBranchNearbyHistory(SaveSearchBranchNearbyHistoryToSaveSearchBranchNearbyHistoryFormMapper.map(parameters))
    }

    data class Params(
        val historyId: HistoryId,
        val resId: RestaurantId,
        val searchBranchNearbyFriend: List<SearchBranchNearbyFriendHistory>
    )
}