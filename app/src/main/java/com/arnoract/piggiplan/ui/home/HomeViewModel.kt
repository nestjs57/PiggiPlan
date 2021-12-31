package com.arnoract.piggiplan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.favorite.ObserveFavoritesUseCase

class HomeViewModel(
    observeFavoritesUseCase: ObserveFavoritesUseCase,
    coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _getFavoritesResult =
        observeFavoritesUseCase.invoke(Unit, coroutinesDispatcherProvider.io).asLiveData().map {
            it.successOr(emptyList())
        }

    val haveFavorite: LiveData<Boolean>
        get() = _getFavoritesResult.map {
            it.any()
        }
}