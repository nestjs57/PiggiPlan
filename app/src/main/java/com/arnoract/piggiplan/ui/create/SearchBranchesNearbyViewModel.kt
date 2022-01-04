package com.arnoract.piggiplan.ui.create

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.history.HistoryId
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.branch.GetBranchesByRestaurantIdUseCase
import com.arnoract.piggiplan.domain.history.GetSearchBranchNearbyHistoryUseCase
import com.arnoract.piggiplan.domain.history.SaveSearchBranchNearbyHistoryUseCase
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.domain.restaurant.GetRestaurantByIdUseCase
import com.arnoract.piggiplan.ui.branch.BranchesNearbyDelegate
import com.arnoract.piggiplan.ui.branch.BranchesNearbyDelegateImpl
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.arnoract.piggiplan.ui.create.model.mapper.SearchBranchNearbyFriendHistoryToUiFriendAddressMapper
import com.arnoract.piggiplan.ui.create.model.mapper.UiFriendAddressToSearchBranchNearbyFriendHistoryMapper
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModelDelegate
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModelDelegateImpl
import com.arnoract.piggiplan.ui.restaurant.model.BranchDistance
import com.arnoract.piggiplan.ui.restaurant.model.mapper.RestaurantToUiRestaurantMapper
import com.arnoract.piggiplan.util.getDistanceMeter
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class SearchBranchesNearbyViewModel(
    private var historyId: HistoryId?,
    private val saveSearchBranchNearbyHistoryUseCase: SaveSearchBranchNearbyHistoryUseCase,
    private val getSearchBranchNearbyHistoryUseCase: GetSearchBranchNearbyHistoryUseCase,
    private val getRestaurantByIdUseCase: GetRestaurantByIdUseCase,
    private val searchBranchesNearbyViewModelDelegateImpl: SearchBranchesNearbyViewModelDelegateImpl,
    private val selectRestaurantViewModelDelegateImpl: SelectRestaurantViewModelDelegateImpl,
    private val branchesNearbyDelegateImpl: BranchesNearbyDelegateImpl,
    private val getBranchesByRestaurantIdUseCase: GetBranchesByRestaurantIdUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), SearchBranchesNearbyViewModelDelegate by searchBranchesNearbyViewModelDelegateImpl,
    SelectRestaurantViewModelDelegate by selectRestaurantViewModelDelegateImpl,
    BranchesNearbyDelegate by branchesNearbyDelegateImpl {

    private val _onNavigateToSelectRestaurant = LiveEvent<RestaurantId>()
    val onNavigateToSelectRestaurant: LiveData<RestaurantId>
        get() = _onNavigateToSelectRestaurant

    private val _isIncompleteDataEvent = LiveEvent<Unit>()
    val isIncompleteDataEvent: LiveEvent<Unit>
        get() = _isIncompleteDataEvent

    private val _calculateBranchesNearbySuccessEvent = LiveEvent<Unit>()
    val calculateBranchesNearbySuccessEvent: LiveData<Unit>
        get() = _calculateBranchesNearbySuccessEvent

    private val _getSearchBranchNearbyHistorySuccessEvent = LiveEvent<Unit>()
    val getSearchBranchNearbyHistorySuccessEvent: LiveData<Unit>
        get() = _getSearchBranchNearbyHistorySuccessEvent

    private val _searchingBranchNearbyEvent = LiveEvent<Boolean>()
    val searchingBranchNearbyEvent: LiveData<Boolean>
        get() = _searchingBranchNearbyEvent

    init {
        if (historyId.isNullOrBlank()) {
            setFriends(listOf())
            setRestaurantSelected(null)
            setBranches(listOf())
        } else {
            getSearchBranchNearbyHistory()
        }
    }

    private fun getSearchBranchNearbyHistory() {
        viewModelScope.launch {
            val searchBranchNearbyHistory = withContext(coroutinesDispatcherProvider.io) {
                getSearchBranchNearbyHistoryUseCase.invoke(historyId ?: "").successOr(null)
            }
            val restaurant = withContext(coroutinesDispatcherProvider.io) {
                getRestaurantByIdUseCase.invoke(searchBranchNearbyHistory?.restaurantId ?: 0L)
                    .successOr(null)
            }
            val friends = searchBranchNearbyHistory?.searchBranchNearbyFriends?.map {
                SearchBranchNearbyFriendHistoryToUiFriendAddressMapper.map(it)
            }
            setFriends(friends ?: listOf())
            setRestaurantSelected(RestaurantToUiRestaurantMapper.map(restaurant))
            setBranches(listOf())
        }
    }

    fun saveSearchBranchNearbyHistory() {
        viewModelScope.launch {
            if (getRestaurantSelected()?.id == null || getFriends().isNullOrEmpty()) {
                _isIncompleteDataEvent.value = Unit
            } else {
                withContext(coroutinesDispatcherProvider.io) {
                    historyId = if (historyId.isNullOrBlank())
                        UUID.randomUUID().toString()
                    else historyId

                    val param = SaveSearchBranchNearbyHistoryUseCase.Params(
                        historyId = historyId ?: "",
                        resId = getRestaurantSelected()?.id ?: 0,
                        searchBranchNearbyFriend = getFriends()?.map {
                            UiFriendAddressToSearchBranchNearbyFriendHistoryMapper.map(it)
                        } ?: listOf()
                    )
                    saveSearchBranchNearbyHistoryUseCase.invoke(param)
                }
                _getSearchBranchNearbyHistorySuccessEvent.value = Unit
            }
        }
    }

    fun searchRestaurantNearByFriends() {
        viewModelScope.launch {
            _searchingBranchNearbyEvent.value = true
            if (getRestaurantSelected()?.id == null || getFriends().isNullOrEmpty()) {
                _isIncompleteDataEvent.value = Unit
            } else {
                val branches = withContext(coroutinesDispatcherProvider.io) {
                    getBranchesByRestaurantIdUseCase.invoke(getRestaurantSelected()?.id ?: 0L)
                        .successOr(
                            emptyList()
                        )
                }
                calculateBranchesNearbyFriends(getFriends(), branches)
                delay(1000)
                _searchingBranchNearbyEvent.value = false
                _calculateBranchesNearbySuccessEvent.value = Unit
            }
        }
    }

    fun navigateToSelectRestaurant() {
        _onNavigateToSelectRestaurant.value = getRestaurantSelected()?.id
    }

    private fun calculateBranchesNearbyFriends(
        friends: List<UiFriendAddress>?,
        branches: List<Branch>
    ) {
        val branchDistance: MutableList<BranchDistance> = mutableListOf()
        branches.forEach { branch ->
            var totalDistance = 0.0
            friends?.forEach { friend ->
                val locationFriend = Location("location_friend").apply {
                    latitude = friend.latLng.latitude
                    longitude = friend.latLng.longitude
                }
                val locationBranch = Location("location_branch").apply {
                    latitude = branch.latitude
                    longitude = branch.longitude
                }
                totalDistance = totalDistance.plus(getDistanceMeter(locationFriend, locationBranch))
            }
            branchDistance.add(BranchDistance(branch, totalDistance))
        }
        setBranches(branchDistance.toList().sortedBy { it.totalKm }.map {
            it.branch
        }.take(5))
    }
}