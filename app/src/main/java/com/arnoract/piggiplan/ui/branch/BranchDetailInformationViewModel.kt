package com.arnoract.piggiplan.ui.branch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.core.successOr
import com.arnoract.piggiplan.domain.branch.GetBranchByBranchIdUseCase
import com.google.android.gms.maps.model.LatLng
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BranchDetailInformationViewModel(
    private val branchId: BranchId,
    private val getBranchByBranchIdUseCase: GetBranchByBranchIdUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _photoImage = MutableLiveData<String>()
    val photoImage: LiveData<String>
        get() = _photoImage

    private val _branchName = MutableLiveData<String>()
    val branchName: LiveData<String>
        get() = _branchName

    private val _branchDescription = MutableLiveData<String>()
    val branchDescription: LiveData<String>
        get() = _branchDescription

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String>
        get() = _phoneNumber

    private val _openTelephoneEvent = LiveEvent<String>()
    val openTelephoneEvent: LiveData<String>
        get() = _openTelephoneEvent

    private val _openGoogleMapEvent = LiveEvent<LatLng>()
    val openGoogleMapEvent: LiveData<LatLng>
        get() = _openGoogleMapEvent

    private val _latLng = MutableLiveData<LatLng>()
    val latLng: LiveData<LatLng>
        get() = _latLng

    init {
        viewModelScope.launch {
            val branch = withContext(coroutinesDispatcherProvider.io) {
                getBranchByBranchIdUseCase.invoke(branchId).successOr(null)
            }
            _photoImage.value = branch?.photoImage
            _branchName.value = branch?.branchName ?: "-"
            _branchDescription.value = branch?.description ?: "-"
            _phoneNumber.value = branch?.tel ?: "-"
            _latLng.value = LatLng(branch?.latitude ?: 0.0, branch?.longitude ?: 0.0)
        }
    }

    fun openTelephone() {
        _openTelephoneEvent.value = _phoneNumber.value
    }

    fun openGoogleMap() {
        _openGoogleMapEvent.value = _latLng.value
    }
}