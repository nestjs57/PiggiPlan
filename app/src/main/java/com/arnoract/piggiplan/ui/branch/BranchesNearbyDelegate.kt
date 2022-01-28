package com.arnoract.piggiplan.ui.branch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnoract.piggiplan.domain.model.branch.Branch

interface BranchesNearbyDelegate {
    val branches: LiveData<List<Branch>>
    fun setBranches(branches: List<Branch>)
    fun getBranches(): List<Branch>
}

class BranchesNearbyDelegateImpl : BranchesNearbyDelegate {

    private val _branches = MutableLiveData<List<Branch>>()
    override val branches: LiveData<List<Branch>>
        get() = _branches

    init {
        _branches.value = listOf()
    }

    override fun setBranches(branches: List<Branch>) {
        _branches.value = branches
    }

    override fun getBranches(): List<Branch> {
        return _branches.value ?: emptyList()
    }
}