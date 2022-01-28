package com.arnoract.piggiplan.domain.model.branch

import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

data class Branch(
    val branchId: BranchId,
    val photoImage: String,
    val resId: RestaurantId,
    val branchName: String,
    val addressName: String,
    val latitude: Double,
    val longitude: Double,
    val tel: String,
    val description: String
)