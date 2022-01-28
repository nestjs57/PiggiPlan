package com.arnoract.piggiplan.domain.model.history.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.history.HistoryEntity
import com.arnoract.piggiplan.domain.model.history.SaveSearchBranchNearbyHistoryForm

object SaveSearchBranchNearbyHistoryFormToHistoryEntityMapper :
    Mapper<SaveSearchBranchNearbyHistoryForm, HistoryEntity> {
    override fun map(from: SaveSearchBranchNearbyHistoryForm): HistoryEntity {
        return HistoryEntity(
            historyId = from.historyId,
            resId = from.resId
        )
    }
}