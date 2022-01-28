package com.arnoract.piggiplan.domain.model.favorite

import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.db.model.favorite.FavoriteId

data class Favorite(
    val id: FavoriteId,
    val branchId: BranchId
)
