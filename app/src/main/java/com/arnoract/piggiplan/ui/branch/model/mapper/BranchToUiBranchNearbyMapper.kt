package com.arnoract.piggiplan.ui.branch.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.branch.model.UiBranchNearby

object BranchToUiBranchNearbyMapper : Mapper<Branch, UiBranchNearby> {
    override fun map(from: Branch): UiBranchNearby {
        return UiBranchNearby(
            id = from.branchId,
            photoImage = from.photoImage,
            branchName = from.branchName,
            description = from.description,
            isFavorite = false
        )
    }
}