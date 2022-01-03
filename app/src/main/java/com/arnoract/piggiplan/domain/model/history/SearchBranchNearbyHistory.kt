package com.arnoract.piggiplan.domain.model.history

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

data class SearchBranchNearbyHistory(
    val restaurantId: RestaurantId,
    val searchBranchNearbyFriends: List<SearchBranchNearbyFriendHistory>
) {
}