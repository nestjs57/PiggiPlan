package com.arnoract.piggiplan.domain.model.history.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.history.FriendHistoryEntity
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyFriendHistory

class SaveBranchNearbyHistoryFormToFriendHistoryEntityMapper(private val historyId: HistoryId) :
    Mapper<SearchBranchNearbyFriendHistory, FriendHistoryEntity> {
    override fun map(from: SearchBranchNearbyFriendHistory): FriendHistoryEntity {
        return FriendHistoryEntity(
            historyId = historyId,
            name = from.name,
            addressId = from.addressId,
            addressName = from.addressName,
            latitude = from.latitude,
            longitude = from.longitude
        )
    }
}