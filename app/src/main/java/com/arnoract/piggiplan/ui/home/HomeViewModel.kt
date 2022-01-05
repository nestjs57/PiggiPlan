package com.arnoract.piggiplan.ui.home

import androidx.lifecycle.*
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.branch.GetBranchesUseCase
import com.arnoract.piggiplan.domain.favorite.ObserveFavoritesUseCase
import com.arnoract.piggiplan.domain.history.ObserveHistoriesUseCase
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.branch.model.UiBranch
import com.arnoract.piggiplan.ui.branch.model.mapper.BranchToUiBranchMapper
import com.arnoract.piggiplan.util.debounce
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    observeFavoritesUseCase: ObserveFavoritesUseCase,
    observeHistoriesUseCase: ObserveHistoriesUseCase,
    private val getBranchesUseCase: GetBranchesUseCase,
    coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _getFavoritesResult =
        observeFavoritesUseCase.invoke(Unit, coroutinesDispatcherProvider.io).asLiveData().map {
            it.successOr(emptyList())
        }

    private val _getHistoryResult =
        observeHistoriesUseCase.invoke(Unit, coroutinesDispatcherProvider.io).asLiveData().map {
            it.successOr(emptyList())
        }

    private val _branchesResult = MutableLiveData<List<Branch>?>()

    private val _navigateSearchBranchNearbyEvent = LiveEvent<HistoryId>()
    val navigateSearchBranchNearbyEvent: LiveData<HistoryId>
        get() = _navigateSearchBranchNearbyEvent

    val haveFavorite: LiveData<Boolean>
        get() = _getFavoritesResult.map {
            it.any()
        }

    val hasHistories: LiveData<Boolean>
        get() = _getHistoryResult.map {
            it.any()
        }

    private val _branchNameQuery = MutableLiveData<String>()

    private val _uiBranches = MediatorLiveData<List<UiBranch>>()
    val uiBranches: LiveData<List<UiBranch>>
        get() = _uiBranches

    init {
        viewModelScope.launch {
            _branchesResult.value = withContext(coroutinesDispatcherProvider.io) {
                getBranchesUseCase.invoke(Unit).successOr(listOf())
            }
        }
        _uiBranches.addSource(_branchesResult) {
            _uiBranches.value = it?.map { branch ->
                BranchToUiBranchMapper.map(branch)
            }
        }
        _uiBranches.addSource(
            _branchNameQuery.debounce(
                coroutineScope = viewModelScope,
                duration = 500
            )
        ) {
            _uiBranches.value = _branchesResult.value?.filter { branch ->
                branch.branchName.contains(it, true)
            }?.map { branch ->
                BranchToUiBranchMapper.map(branch)
            }
        }
    }

    fun navigationToSearchBranchNearby() {
        _navigateSearchBranchNearbyEvent.value =
            _getHistoryResult.value?.firstOrNull()?.historyId ?: ""
    }

    fun filterBranch(query: String) {
        _branchNameQuery.value = query
    }
}