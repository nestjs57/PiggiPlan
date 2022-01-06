package com.arnoract.piggiplan.core.api.branch.model

import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.google.gson.annotations.SerializedName

data class BranchResponse(
    @field:SerializedName("branchId")
    var branchId: BranchId = 0,
    @field:SerializedName("resId")
    var restaurantId: RestaurantId? = null,
    @field:SerializedName("photoImage")
    var photoImage: String? = null,
    @field:SerializedName("branchName")
    var branchName: String? = null,
    @field:SerializedName("addressName")
    var addressName: String? = null,
    @field:SerializedName("latitude")
    var latitude: Double? = null,
    @field:SerializedName("longitude")
    var longitude: Double? = null,
    @field:SerializedName("tel")
    var tel: String? = null,
    @field:SerializedName("description")
    var description: String? = null,
)