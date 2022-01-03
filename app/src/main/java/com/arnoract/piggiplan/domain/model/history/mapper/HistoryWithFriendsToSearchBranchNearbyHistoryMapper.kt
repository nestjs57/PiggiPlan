package com.arnoract.piggiplan.domain.model.history.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.history.HistoryWithFriendsHistory
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyHistory

object HistoryWithFriendsToSearchBranchNearbyHistoryMapper : Mapper<HistoryWithFriendsHistory, SearchBranchNearbyHistory> {
    override fun map(from: HistoryWithFriendsHistory): SearchBranchNearbyHistory {
        return SearchBranchNearbyHistory(
            restaurantId = from.history.resId,
            searchBranchNearbyFriends = from.friendsHistory.map {
                FriendHistoryEntityToSearchBranchNearbyFriendHistoryMapper.map(it)
            }
        )
    }
}