package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng

interface SelectAddressViewModelDelegate {
    val addressId: LiveData<String?>
    val addressName: LiveData<String?>
    val latLng: LiveData<LatLng?>
    fun setAddressId(id: String?)
    fun setAddressName(addressName: String?)
    fun getAddressName(): String?
    fun setLatLng(latLng: LatLng?)
    fun getLatLng(): LatLng?
}

class SelectAddressViewModelDelegateImpl : SelectAddressViewModelDelegate {

    private val _addressId = MutableLiveData<String?>()
    override val addressId: LiveData<String?>
        get() = _addressId

    private val _addressName = MutableLiveData<String?>()
    override val addressName: LiveData<String?>
        get() = _addressName

    private val _latLng = MutableLiveData<LatLng?>()
    override val latLng: LiveData<LatLng?>
        get() = _latLng

    override fun setAddressId(id: String?) {
        _addressId.value = id
    }

    override fun setAddressName(addressName: String?) {
        _addressName.value = addressName
    }

    override fun getAddressName(): String? {
        return _addressName.value
    }

    override fun setLatLng(latLng: LatLng?) {
        _latLng.value = latLng
    }

    override fun getLatLng(): LatLng? {
        return _latLng.value
    }
}