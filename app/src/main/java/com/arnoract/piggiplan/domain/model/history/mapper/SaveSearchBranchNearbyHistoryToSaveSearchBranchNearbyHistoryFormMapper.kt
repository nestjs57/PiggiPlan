package com.arnoract.piggiplan.domain.model.history.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.history.SaveSearchBranchNearbyHistoryUseCase
import com.arnoract.piggiplan.domain.model.history.SaveSearchBranchNearbyHistoryForm

object SaveSearchBranchNearbyHistoryToSaveSearchBranchNearbyHistoryFormMapper : Mapper<SaveSearchBranchNearbyHistoryUseCase.Params, SaveSearchBranchNearbyHistoryForm> {
    override fun map(from: SaveSearchBranchNearbyHistoryUseCase.Params): SaveSearchBranchNearbyHistoryForm {
        return SaveSearchBranchNearbyHistoryForm(
            historyId = from.historyId,
            resId = from.resId,
            searchBranchNearbyFriend = from.searchBranchNearbyFriend
        )
    }
}