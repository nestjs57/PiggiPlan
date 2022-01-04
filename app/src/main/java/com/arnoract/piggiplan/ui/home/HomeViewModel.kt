package com.arnoract.piggiplan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.favorite.ObserveFavoritesUseCase
import com.arnoract.piggiplan.domain.history.ObserveHistoriesUseCase
import com.hadilq.liveevent.LiveEvent

class HomeViewModel(
    observeFavoritesUseCase: ObserveFavoritesUseCase,
    observeHistoriesUseCase: ObserveHistoriesUseCase,
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

    fun navigationToSearchBranchNearby() {
        _navigateSearchBranchNearbyEvent.value =
            _getHistoryResult.value?.firstOrNull()?.historyId ?: ""
    }
}