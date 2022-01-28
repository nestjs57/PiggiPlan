package com.arnoract.piggiplan.ui.create.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.history.SearchBranchNearbyFriendHistory
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.google.android.gms.maps.model.LatLng

object SearchBranchNearbyFriendHistoryToUiFriendAddressMapper : Mapper<SearchBranchNearbyFriendHistory, UiFriendAddress> {
    override fun map(from: SearchBranchNearbyFriendHistory): UiFriendAddress {
        return UiFriendAddress(
            id = from.id,
            name = from.name,
            addressId = from.addressId,
            addressName = from.addressName,
            latLng = LatLng(from.latitude, from.longitude)
        )
    }
}