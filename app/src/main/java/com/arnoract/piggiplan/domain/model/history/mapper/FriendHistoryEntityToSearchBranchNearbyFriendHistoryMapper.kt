package com.arnoract.piggiplan.domain.model.history.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.history.FriendHistoryEntity
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyFriendHistory

object FriendHistoryEntityToSearchBranchNearbyFriendHistoryMapper : Mapper<FriendHistoryEntity, SearchBranchNearbyFriendHistory> {
    override fun map(from: FriendHistoryEntity): SearchBranchNearbyFriendHistory {
        return SearchBranchNearbyFriendHistory(
            id = from.friendHistoryId,
            name = from.name,
            addressId = from.addressId,
            addressName = from.addressName,
            latitude = from.latitude,
            longitude = from.longitude
        )
    }
}