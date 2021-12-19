package com.arnoract.piggiplan.ui.create.model

import com.google.android.gms.maps.model.LatLng

data class UiFriendAddress(
    val id: Long = 0L,
    val name: String,
    val addressId: String,
    val addressName: String,
    val latLng: LatLng
)