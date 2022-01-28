package com.arnoract.piggiplan.ui.restaurant.model

import com.arnoract.piggiplan.domain.model.branch.Branch

data class BranchDistance(
    val branch: Branch,
    val totalKm: Double
)