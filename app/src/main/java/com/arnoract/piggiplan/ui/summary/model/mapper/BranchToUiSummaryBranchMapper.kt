package com.arnoract.piggiplan.ui.summary.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.summary.model.UiSummaryBranch

object BranchToUiSummaryBranchMapper : Mapper<Branch, UiSummaryBranch> {
    override fun map(from: Branch): UiSummaryBranch {
        return UiSummaryBranch(
            branchId = from.branchId,
            photoImage = from.photoImage,
            branchName = from.branchName,
            description = from.description
        )
    }
}