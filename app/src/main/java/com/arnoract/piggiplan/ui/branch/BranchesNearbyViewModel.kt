package com.arnoract.piggiplan.ui.branch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arnoract.piggiplan.ui.branch.model.BranchToUiBranchNearbyMapper
import com.arnoract.piggiplan.ui.branch.model.UiBranchNearby
import com.arnoract.piggiplan.util.setValueIfNew

class BranchesNearbyViewModel(
    private val branchesNearbyDelegateImpl: BranchesNearbyDelegateImpl,
) : ViewModel(), BranchesNearbyDelegate by branchesNearbyDelegateImpl {

    private val _uiBranchesNearBy = MutableLiveData<List<UiBranchNearby>>()
    val uiBranchesNearBy: LiveData<List<UiBranchNearby>>
        get() = _uiBranchesNearBy

    init {
        _uiBranchesNearBy.setValueIfNew(getBranches().map {
            BranchToUiBranchNearbyMapper.map(it)
        })
    }
}