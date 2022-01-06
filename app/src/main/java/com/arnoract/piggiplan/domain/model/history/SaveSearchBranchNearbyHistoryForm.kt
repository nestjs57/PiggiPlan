package com.arnoract.piggiplan.domain.model.history

import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

data class SaveSearchBranchNearbyHistoryForm(
    val historyId: HistoryId,
    val resId: RestaurantId,
    val searchBranchNearbyFriend: List<SearchBranchNearbyFriendHistory>
)
