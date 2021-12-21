package com.arnoract.piggiplan.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.test.model.RestaurantTestData
import com.arnoract.piggiplan.ui.restaurant.model.UiRestaurant
import com.arnoract.piggiplan.ui.restaurant.model.mapper.RestaurantToUiRestaurantMapper

class SelectRestaurantViewModel(
    private val restaurantId: RestaurantId,
) : ViewModel() {

    private val _restaurants = MutableLiveData<List<UiRestaurant>>()
    val restaurants: LiveData<List<UiRestaurant>>
        get() = _restaurants

    init {
        _restaurants.value = RestaurantTestData.all().map {
            RestaurantToUiRestaurantMapper.map(it).copy(isSelected = it.id == restaurantId)
        }
    }

    fun selectRestaurant(id: RestaurantId) {
        _restaurants.value = _restaurants.value?.map {
            it.copy(isSelected = it.id == id)
        }
    }
}