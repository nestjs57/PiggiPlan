package com.arnoract.piggiplan.domain.model.history

data class SearchBranchNearbyFriendHistory(
    val id: Long = 0L,
    val name: String,
    val addressId: String,
    val addressName: String,
    val latitude: Double,
    val longitude: Double
)
