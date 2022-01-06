package com.arnoract.piggiplan.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.branch.BranchesNearbyDelegate
import com.arnoract.piggiplan.ui.branch.BranchesNearbyDelegateImpl
import com.arnoract.piggiplan.ui.create.SearchBranchesNearbyViewModelDelegate
import com.arnoract.piggiplan.ui.create.SearchBranchesNearbyViewModelDelegateImpl
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.arnoract.piggiplan.ui.summary.model.UiSummaryBranch
import com.arnoract.piggiplan.ui.summary.model.mapper.BranchToUiSummaryBranchMapper
import com.arnoract.piggiplan.util.setValueIfNew
import com.google.android.gms.maps.model.LatLng
import com.hadilq.liveevent.LiveEvent

class SummaryViewModel(
    private val branchesNearbyDelegateImpl: BranchesNearbyDelegateImpl,
    private val searchBranchesNearbyViewModelDelegateImpl: SearchBranchesNearbyViewModelDelegateImpl,
) : ViewModel(), BranchesNearbyDelegate by branchesNearbyDelegateImpl,
    SearchBranchesNearbyViewModelDelegate by searchBranchesNearbyViewModelDelegateImpl {

    private val _allFriends = LiveEvent<List<UiFriendAddress>?>()
    val allFriend: LiveData<List<UiFriendAddress>?>
        get() = _allFriends

    private val _allBranches = LiveEvent<List<Branch>>()
    val allBranches: LiveData<List<Branch>>
        get() = _allBranches

    private val _uiSummaryBranch = MutableLiveData<List<UiSummaryBranch>>()
    val uiSummaryBranch: LiveData<List<UiSummaryBranch>>
        get() = _uiSummaryBranch

    private val _moveCameraEvent = LiveEvent<LatLng>()
    val moveCameraEvent: LiveData<LatLng>
        get() = _moveCameraEvent

    private val _moveCameraAllBoundsEvent = LiveEvent<List<LatLng>>()
    val moveCameraAllBoundsEvent: LiveData<List<LatLng>>
        get() = _moveCameraAllBoundsEvent

    private val _onBranchScrollToPositionEvent = LiveEvent<Int>()
    val onBranchScrollToPositionEvent: LiveData<Int>
        get() = _onBranchScrollToPositionEvent

    fun onMapReady() {
        _allFriends.setValueIfNew(getFriends())
        _allBranches.setValueIfNew(getBranches())
        _uiSummaryBranch.setValueIfNew(getBranches().map { BranchToUiSummaryBranchMapper.map(it) })
    }

    fun moveCamera(position: Int) {
        val branch = _allBranches.value?.get(position)
        _moveCameraEvent.setValueIfNew(
            LatLng(
                branch?.latitude ?: 0.0,
                branch?.longitude ?: 0.0
            )
        )
    }

    fun moveCameraAllBounds() {
        val allFriendsLatLng = _allFriends.value?.map { it.latLng }
        val allBranchesLatLng = _allBranches.value?.map {
            LatLng(it.latitude, it.longitude)
        }
        _moveCameraAllBoundsEvent.value = allFriendsLatLng?.plus(allBranchesLatLng ?: listOf())
    }

    fun onMarkerSelected(branchId: BranchId) {
        val markerPositionSelected = _allBranches.value?.indexOfLast { it.branchId == branchId }
        _onBranchScrollToPositionEvent.value = markerPositionSelected ?: 0
    }
}