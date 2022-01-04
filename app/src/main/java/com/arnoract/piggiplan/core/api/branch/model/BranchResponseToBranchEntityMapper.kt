package com.arnoract.piggiplan.core.api.branch.model

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.branch.BranchEntity

object BranchResponseToBranchEntityMapper : Mapper<BranchResponse, BranchEntity> {
    override fun map(from: BranchResponse): BranchEntity {
        return BranchEntity(
            branchId = from.branchId,
            photo = from.photoImage ?: "",
            resId = from.restaurantId ?: 0L,
            branchName = from.branchName ?: "",
            addressName = from.addressName ?: "",
            latitude = from.latitude ?: 0.0,
            longitude = from.longitude ?: 0.0,
            tel = from.tel ?: "",
            description = from.description ?: ""
        )
    }
}