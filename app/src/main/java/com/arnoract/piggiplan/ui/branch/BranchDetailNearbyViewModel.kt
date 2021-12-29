package com.arnoract.piggiplan.ui.branch

import androidx.lifecycle.ViewModel
import com.arnoract.piggiplan.core.db.model.branch.BranchId

class BranchDetailNearbyViewModel(
    private val branchId: BranchId
) : ViewModel()