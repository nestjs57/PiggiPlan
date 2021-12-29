package com.arnoract.piggiplan.ui.branch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.branch.GetBranchByBranchIdUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BranchDetailViewModel(
    private val branchId: BranchId,
    private val getBranchByBranchIdUseCase: GetBranchByBranchIdUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _branchName = MutableLiveData<String>()
    val branchName: LiveData<String>
        get() = _branchName

    init {
        viewModelScope.launch {
            val branch = withContext(coroutinesDispatcherProvider.io) {
                getBranchByBranchIdUseCase.invoke(branchId).successOr(null)
            }
            _branchName.value = branch?.branchName ?: "-"
        }
    }
}