package com.arnoract.piggiplan.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.branch.BranchesNearbyDelegate
import com.arnoract.piggiplan.ui.branch.BranchesNearbyDelegateImpl
import com.arnoract.piggiplan.ui.create.SearchBranchesNearbyViewModelDelegate
import com.arnoract.piggiplan.ui.create.SearchBranchesNearbyViewModelDelegateImpl
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress
import com.google.android.gms.maps.model.LatLng
import com.hadilq.liveevent.LiveEvent

class SummaryViewModel(
    private val branchesNearbyDelegateImpl: BranchesNearbyDelegateImpl,
    private val searchBranchesNearbyViewModelDelegateImpl: SearchBranchesNearbyViewModelDelegateImpl,
) : ViewModel(), BranchesNearbyDelegate by branchesNearbyDelegateImpl,
    SearchBranchesNearbyViewModelDelegate by searchBranchesNearbyViewModelDelegateImpl {

    private val _allFriends = MediatorLiveData<List<UiFriendAddress>?>()
    val allFriend: LiveData<List<UiFriendAddress>?>
        get() = _allFriends

    private val _allBranches = MutableLiveData<List<Branch>>()
    val allBranches: LiveData<List<Branch>>
        get() = _allBranches

    private val _moveCameraEvent = LiveEvent<LatLng>()
    val moveCameraEvent: LiveData<LatLng>
        get() = _moveCameraEvent

    fun onMapReady() {
        _allFriends.value = getFriends()
        _allBranches.value = getBranches()

        _moveCameraEvent.value = LatLng(
            _allBranches.value?.first()?.latitude ?: 0.0,
            _allBranches.value?.first()?.longitude ?: 0.0
        )
    }
}