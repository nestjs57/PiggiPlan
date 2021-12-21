package com.arnoract.piggiplan.domain.model.branch.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.branch.BranchEntity
import com.arnoract.piggiplan.domain.model.branch.Branch

object BranchToBranchEntityMapper : Mapper<Branch, BranchEntity> {
    override fun map(from: Branch): BranchEntity {
        return BranchEntity(
            branchId = from.branchId,
            resId = from.resId,
            branchName = from.branchName,
            addressName = from.addressName,
            latitude = from.latitude,
            longitude = from.longitude,
            tel = from.tel,
        )
    }
}