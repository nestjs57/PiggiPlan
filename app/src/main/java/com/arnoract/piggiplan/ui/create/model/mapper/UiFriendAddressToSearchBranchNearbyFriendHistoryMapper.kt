package com.arnoract.piggiplan.ui.create.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyFriendHistory
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress

object UiFriendAddressToSearchBranchNearbyFriendHistoryMapper : Mapper<UiFriendAddress, SearchBranchNearbyFriendHistory> {
    override fun map(from: UiFriendAddress): SearchBranchNearbyFriendHistory {
        return SearchBranchNearbyFriendHistory(
            id = from.id,
            name = from.name,
            addressId = from.addressId,
            addressName = from.addressName,
            latitude = from.latLng.latitude,
            longitude = from.latLng.longitude
        )
    }
}