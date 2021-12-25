package com.arnoract.piggiplan.ui.create

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.branch.GetBranchesByRestaurantIdUseCase
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModelDelegate
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModelDelegateImpl
import com.arnoract.piggiplan.ui.restaurant.model.BranchDistance
import com.arnoract.piggiplan.util.LocationUtil.getDistanceMeter
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreatePartyViewModel(
    private val createPartyViewModelDelegateImpl: CreatePartyViewModelDelegateImpl,
    private val selectRestaurantViewModelDelegateImpl: SelectRestaurantViewModelDelegateImpl,
    private val getBranchesByRestaurantIdUseCase: GetBranchesByRestaurantIdUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), CreatePartyViewModelDelegate by createPartyViewModelDelegateImpl,
    SelectRestaurantViewModelDelegate by selectRestaurantViewModelDelegateImpl {

    private val _onNavigateToSelectRestaurant = LiveEvent<RestaurantId>()
    val onNavigateToSelectRestaurant: LiveData<RestaurantId>
        get() = _onNavigateToSelectRestaurant

    private val _isIncompleteDataEvent = LiveEvent<Unit>()
    val isIncompleteDataEvent: LiveEvent<Unit>
        get() = _isIncompleteDataEvent

    init {
        setFriends(listOf())
        setRestaurantSelected(null)
    }

    fun navigateToSelectRestaurant() {
        _onNavigateToSelectRestaurant.value = getRestaurantSelected()?.id
    }

    fun searchRestaurantNearByFriends() {
        viewModelScope.launch {
            if (getRestaurantSelected()?.id == null || getFriends().isNullOrEmpty()) {
                _isIncompleteDataEvent.value = Unit
            } else {
                val branches = withContext(coroutinesDispatcherProvider.io) {
                    getBranchesByRestaurantIdUseCase.invoke(getRestaurantSelected()?.id ?: 0L)
                        .successOr(
                            emptyList()
                        )
                }
                val friends = getFriends()
                calculateBranchesNearByFriends(friends, branches)
            }
        }
    }

    private fun calculateBranchesNearByFriends(
        friends: List<UiFriendAddress>?,
        branches: List<Branch>
    ) {
        val branchDistance: MutableList<BranchDistance> = mutableListOf()
        branches.forEach { branch ->
            var totalDistance = 0.0
            friends?.forEach { friend ->
                val locationFriend = Location("locationFriend").apply {
                    latitude = friend.latLng.latitude
                    longitude = friend.latLng.longitude
                }
                val locationBranch = Location("locationBranch").apply {
                    latitude = branch.latitude
                    longitude = branch.longitude
                }
                totalDistance = totalDistance.plus(getDistanceMeter(locationFriend, locationBranch))
            }
            branchDistance.add(BranchDistance(branch, totalDistance))
        }
    }
}