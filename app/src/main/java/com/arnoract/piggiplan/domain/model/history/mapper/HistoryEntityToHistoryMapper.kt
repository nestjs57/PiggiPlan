package com.arnoract.piggiplan.domain.model.history.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.history.HistoryEntity
import com.arnoract.piggiplan.domain.model.history.History

object HistoryEntityToHistoryMapper : Mapper<HistoryEntity, History> {
    override fun map(from: HistoryEntity): History {
        return History(
            historyId = from.historyId,
            restaurantId = from.resId
        )
    }
}