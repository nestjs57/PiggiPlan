package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.hadilq.liveevent.LiveEvent

class SelectAddressViewModel(
    private val selectAddressViewModelDelegateImpl: SelectAddressViewModelDelegateImpl
) : ViewModel(), SelectAddressViewModelDelegate by selectAddressViewModelDelegateImpl {

    private var _addressIdSelected: String? = null

    private val _addressNameSelected = MutableLiveData<String?>()
    val addressNameSelected: LiveData<String?>
        get() = _addressNameSelected

    private val _latLngSelected = MutableLiveData<LatLng?>()
    val latLngSelected: LiveData<LatLng?>
        get() = _latLngSelected

    private val _invalidLatLngEvent = LiveEvent<Unit>()
    val invalidLatLngEvent: LiveData<Unit>
        get() = _invalidLatLngEvent

    fun initialData() {
        _addressNameSelected.value = getAddressName()
        _latLngSelected.value = getLatLng()
    }

    fun setAddressSelected(id: String, addressName: String, latLng: LatLng?) {
        _addressIdSelected = id
        _addressNameSelected.value = addressName
        _latLngSelected.value = latLng
    }

    fun saveAddress() {
        setAddressId(_addressIdSelected)
        setAddressName(_addressNameSelected.value)
        setLatLng(_latLngSelected.value)
    }
}