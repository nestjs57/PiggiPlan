package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.hadilq.liveevent.LiveEvent

class SelectAddressViewModel : ViewModel() {

    private val _latLng = MutableLiveData<LatLng?>()
    val latLng: LiveData<LatLng?>
        get() = _latLng

    private val _invalidLatLngEvent = LiveEvent<Unit>()
    val invalidLatLngEvent: LiveData<Unit>
        get() = _invalidLatLngEvent

    private val _addressName = MutableLiveData<String>()
    val addressName: LiveData<String>
        get() = _addressName

    fun setLatLng(latLng: LatLng?) {
        if (latLng == null) {
            _invalidLatLngEvent.value = Unit
        } else {
            _latLng.value = latLng
        }
    }

    fun setAddressName(addressName: String) {
        _addressName.value = addressName
    }
}