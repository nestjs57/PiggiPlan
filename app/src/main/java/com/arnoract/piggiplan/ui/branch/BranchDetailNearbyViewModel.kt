package com.arnoract.piggiplan.ui.branch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.core.successOrThrow
import com.arnoract.piggiplan.domain.branch.GetBranchByBranchIdUseCase
import com.arnoract.piggiplan.domain.branch.GetBranchesByRestaurantIdUseCase
import com.arnoract.piggiplan.ui.branch.model.UiBranchDetailNearby
import com.arnoract.piggiplan.ui.branch.model.mapper.BranchToUiBranchDetailNearbyMapper
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BranchDetailNearbyViewModel(
    private val branchId: BranchId,
    private val getBranchByBranchIdUseCase: GetBranchByBranchIdUseCase,
    private val getBranchesByRestaurantIdUseCase: GetBranchesByRestaurantIdUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _uiBranchDetailNearby = MutableLiveData<List<UiBranchDetailNearby>>()
    val uiBranchDetailNearby: LiveData<List<UiBranchDetailNearby>>
        get() = _uiBranchDetailNearby

    private val _getBranchByBranchIdFailException = LiveEvent<String>()
    val getBranchByBranchIdFailException: LiveData<String>
        get() = _getBranchByBranchIdFailException

    init {
        viewModelScope.launch {
            try {
                val currentBranch = withContext(coroutinesDispatcherProvider.io) {
                    getBranchByBranchIdUseCase.invoke(branchId).successOrThrow()
                }
                val allBranches = withContext(coroutinesDispatcherProvider.io) {
                    getBranchesByRestaurantIdUseCase.invoke(currentBranch.resId)
                        .successOr(emptyList())
                }
                _uiBranchDetailNearby.value = allBranches.map {
                    BranchToUiBranchDetailNearbyMapper.map(
                        BranchToUiBranchDetailNearbyMapper.Params(
                            currentBranch = currentBranch,
                            branch = it
                        )
                    )
                }.filter { it.id != currentBranch.branchId }.sortedBy { it.distance }
            } catch (e: Exception) {
                _getBranchByBranchIdFailException.value = e.message ?: "Unknown Error"
            }
        }
    }
}