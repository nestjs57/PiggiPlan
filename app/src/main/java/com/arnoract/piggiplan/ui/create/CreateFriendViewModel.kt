package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModelDelegate
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModelDelegateImpl
import com.hadilq.liveevent.LiveEvent

class CreateFriendViewModel(
    private val createPartyViewModelDelegateImpl: CreatePartyViewModelDelegateImpl,
    private val selectRestaurantViewModelDelegateImpl: SelectRestaurantViewModelDelegateImpl,
) : ViewModel(), CreatePartyViewModelDelegate by createPartyViewModelDelegateImpl,
    SelectRestaurantViewModelDelegate by selectRestaurantViewModelDelegateImpl {

    private val _onNavigateToSelectRestaurant = LiveEvent<RestaurantId>()
    val onNavigateToSelectRestaurant: LiveData<RestaurantId>
        get() = _onNavigateToSelectRestaurant

    init {
        setFriends(listOf())
        setRestaurantSelected(null)
    }

    fun navigateToSelectRestaurant() {
        _onNavigateToSelectRestaurant.value = getRestaurantSelected()?.id
    }
}