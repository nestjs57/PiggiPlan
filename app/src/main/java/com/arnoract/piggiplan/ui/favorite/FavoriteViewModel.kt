package com.arnoract.piggiplan.ui.favorite

import androidx.lifecycle.*
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.branch.GetBranchesUseCase
import com.arnoract.piggiplan.domain.favorite.DeleteAllFavoritesUseCase
import com.arnoract.piggiplan.domain.favorite.ObserveFavoritesUseCase
import com.arnoract.piggiplan.domain.favorite.UpdateFavoriteUseCase
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.favorite.model.UiFavorite
import com.arnoract.piggiplan.ui.favorite.model.mapper.BranchToUiFavoriteMapper
import com.hadilq.liveevent.LiveEvent
import com.snakydesign.livedataextensions.combineLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val getBranchesUseCase: GetBranchesUseCase,
    private val deleteAllFavoritesUseCase: DeleteAllFavoritesUseCase,
    observeFavoritesUseCase: ObserveFavoritesUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _getFavoritesResult =
        observeFavoritesUseCase.invoke(Unit, coroutinesDispatcherProvider.io).asLiveData().map {
            it.successOr(emptyList())
        }

    private val _branches = MutableLiveData<List<Branch>?>()

    val uiFavorite: LiveData<List<UiFavorite>?> =
        combineLatest(_branches, _getFavoritesResult) { branches, favorites ->
            if (branches.isNullOrEmpty()) return@combineLatest null
            if (favorites == null) return@combineLatest null

            _branches.value?.map { branch ->
                val uiFavorite = BranchToUiFavoriteMapper.map(branch)
                if (favorites.any { it.branchId == branch.branchId }) {
                    uiFavorite.copy(isFavorite = true)
                } else {
                    uiFavorite
                }
            }?.filter { it.isFavorite }
        }

    private val _showAlertConfirmDeleteAllFavoriteEvent = LiveEvent<Unit>()
    val showAlertConfirmDeleteAllFavoriteEvent: LiveData<Unit>
        get() = _showAlertConfirmDeleteAllFavoriteEvent

    private val _isEmptyFavorites = MediatorLiveData<Boolean>()
    val isEmptyFavorites: LiveData<Boolean>
        get() = _isEmptyFavorites

    init {
        viewModelScope.launch {
            _branches.value = withContext(coroutinesDispatcherProvider.io) {
                getBranchesUseCase.invoke(Unit).successOr(emptyList())
            }
        }
        _isEmptyFavorites.addSource(_getFavoritesResult) {
            _isEmptyFavorites.value = it.isNullOrEmpty()
        }
    }

    fun updateFavorite(branchId: BranchId) {
        viewModelScope.launch {
            withContext(coroutinesDispatcherProvider.io) {
                updateFavoriteUseCase.invoke(branchId)
            }
        }
    }

    fun showConfirmDeleteAllFavorite() {
        _showAlertConfirmDeleteAllFavoriteEvent.value = Unit
    }

    fun deleteAllFavorite() {
        viewModelScope.launch {
            withContext(coroutinesDispatcherProvider.io) {
                deleteAllFavoritesUseCase.invoke(Unit)
            }
        }
    }
}