package com.arnoract.piggiplan.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.core.successOrThrow
import com.arnoract.piggiplan.domain.restaurant.GetRestaurantsUseCase
import com.arnoract.piggiplan.ui.restaurant.model.UiRestaurant
import com.arnoract.piggiplan.ui.restaurant.model.mapper.RestaurantToUiRestaurantMapper
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectRestaurantViewModel(
    private val restaurantId: RestaurantId,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val selectRestaurantViewModelDelegateImpl: SelectRestaurantViewModelDelegateImpl,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), SelectRestaurantViewModelDelegate by selectRestaurantViewModelDelegateImpl {

    private val _restaurants = MutableLiveData<List<UiRestaurant>>()
    val restaurants: LiveData<List<UiRestaurant>>
        get() = _restaurants

    private val _getRestaurantFailEvent = LiveEvent<String>()
    val getRestaurantFailEvent: LiveData<String>
        get() = _getRestaurantFailEvent

    init {
        viewModelScope.launch {
            try {
                _restaurants.value = withContext(coroutinesDispatcherProvider.io) {
                    getRestaurantsUseCase.invoke(Unit).successOrThrow()
                }.map {
                    RestaurantToUiRestaurantMapper.map(it).copy(isSelected = it.id == restaurantId)
                }
            } catch (e: Exception) {
                _getRestaurantFailEvent.value = e.message ?: "Unknown Error"
            }
        }
    }

    fun selectRestaurant(id: RestaurantId) {
        _restaurants.value = _restaurants.value?.map {
            it.copy(isSelected = it.id == id)
        }
    }

    fun saveRestaurant() {
        val restaurantSelected = _restaurants.value?.firstOrNull { it.isSelected }
        setRestaurantSelected(restaurantSelected)
    }
}