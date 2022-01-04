package com.arnoract.piggiplan.domain.model.history

import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

data class History(
    val historyId: HistoryId,
    val restaurantId: RestaurantId
)
