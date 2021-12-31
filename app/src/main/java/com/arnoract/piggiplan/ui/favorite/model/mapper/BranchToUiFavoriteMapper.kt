package com.arnoract.piggiplan.ui.favorite.model.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.favorite.model.UiFavorite

object BranchToUiFavoriteMapper : Mapper<Branch, UiFavorite> {
    override fun map(from: Branch): UiFavorite {
        return UiFavorite(
            id = from.branchId,
            photoImage = from.photoImage,
            branchName = from.branchName,
            description = from.description,
            isFavorite = false
        )
    }
}