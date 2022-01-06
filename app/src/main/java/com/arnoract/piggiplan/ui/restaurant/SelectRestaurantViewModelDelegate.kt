package com.arnoract.piggiplan.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnoract.piggiplan.ui.restaurant.model.UiRestaurant

interface SelectRestaurantViewModelDelegate {
    val restaurantSelected: LiveData<UiRestaurant?>
    fun setRestaurantSelected(restaurant: UiRestaurant?)
    fun getRestaurantSelected(): UiRestaurant?
}

class SelectRestaurantViewModelDelegateImpl : SelectRestaurantViewModelDelegate {

    private val _restaurantSelected = MutableLiveData<UiRestaurant?>()
    override val restaurantSelected: LiveData<UiRestaurant?>
        get() = _restaurantSelected

    override fun setRestaurantSelected(restaurant: UiRestaurant?) {
        _restaurantSelected.value = restaurant
    }

    override fun getRestaurantSelected(): UiRestaurant? {
        return _restaurantSelected.value
    }


}