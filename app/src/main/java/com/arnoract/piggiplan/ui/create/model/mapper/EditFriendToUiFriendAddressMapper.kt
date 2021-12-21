package com.arnoract.piggiplan.ui.create.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.google.android.gms.maps.model.LatLng

object EditFriendToUiFriendAddressMapper :
    Mapper<EditFriendToUiFriendAddressMapper.Params, UiFriendAddress> {

    override fun map(from: Params): UiFriendAddress {
        return UiFriendAddress(
            name = from.name,
            addressId = from.addressId ?: "",
            addressName = from.addressName ?: "",
            latLng = from.latLng ?: LatLng(0.0, 0.0)
        )
    }

    data class Params(
        val name: String,
        val addressId: String?,
        val addressName: String?,
        val latLng: LatLng?
    )
}