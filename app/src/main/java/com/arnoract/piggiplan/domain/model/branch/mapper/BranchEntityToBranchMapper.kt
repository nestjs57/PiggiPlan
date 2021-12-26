package com.arnoract.piggiplan.domain.model.branch.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.branch.BranchEntity
import com.arnoract.piggiplan.domain.model.branch.Branch

object BranchEntityToBranchMapper : Mapper<BranchEntity, Branch> {
    override fun map(from: BranchEntity): Branch {
        return Branch(
            branchId = from.branchId,
            resId = from.resId,
            branchName = from.branchName,
            addressName = from.addressName,
            latitude = from.latitude,
            longitude = from.longitude,
            tel = from.tel,
            description = from.description
        )
    }
}