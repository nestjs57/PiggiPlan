package com.arnoract.piggiplan.ui.branch

import androidx.lifecycle.*
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.favorite.ObserveFavoritesUseCase
import com.arnoract.piggiplan.domain.favorite.UpdateFavoriteUseCase
import com.arnoract.piggiplan.ui.branch.model.UiBranchNearby
import com.arnoract.piggiplan.ui.branch.model.mapper.BranchToUiBranchNearbyMapper
import com.arnoract.piggiplan.util.setValueIfNew
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BranchesNearbyViewModel(
    private val branchesNearbyDelegateImpl: BranchesNearbyDelegateImpl,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    observeFavoritesUseCase: ObserveFavoritesUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), BranchesNearbyDelegate by branchesNearbyDelegateImpl {

    private val _getFavoritesResult =
        observeFavoritesUseCase.invoke(Unit, coroutinesDispatcherProvider.io).asLiveData().map {
            it.successOr(emptyList())
        }

    private val _uiBranchesNearBy = MediatorLiveData<List<UiBranchNearby>>()
    val uiBranchesNearBy: LiveData<List<UiBranchNearby>>
        get() = _uiBranchesNearBy

    init {
        _uiBranchesNearBy.addSource(_getFavoritesResult) { favorites ->
            _uiBranchesNearBy.setValueIfNew(getBranches().map { branch ->
                BranchToUiBranchNearbyMapper.map(branch)
                    .copy(isFavorite = favorites.any { it.branchId == branch.branchId })
            })
        }
    }

    fun updateFavorite(branchId: BranchId) {
        viewModelScope.launch {
            withContext(coroutinesDispatcherProvider.io) {
                updateFavoriteUseCase.invoke(branchId)
            }
            _uiBranchesNearBy.value = _uiBranchesNearBy.value?.map {
                if (it.id == branchId) {
                    it.copy(isFavorite = !it.isFavorite)
                } else {
                    it
                }
            }
        }
    }
}