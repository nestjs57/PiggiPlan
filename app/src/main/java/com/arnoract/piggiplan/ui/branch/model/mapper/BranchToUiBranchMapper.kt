package com.arnoract.piggiplan.ui.branch.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.branch.model.UiBranch

object BranchToUiBranchMapper : Mapper<Branch, UiBranch> {
    override fun map(from: Branch): UiBranch {
        return UiBranch(
            id = from.branchId,
            photoImage = from.photoImage,
            branchName = from.branchName,
            description = from.description,
        )
    }
}