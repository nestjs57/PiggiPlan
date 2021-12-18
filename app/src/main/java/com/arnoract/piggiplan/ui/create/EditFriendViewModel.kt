package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditFriendViewModel(
    userId: Long,
    private val createPartyViewModelDelegateImpl: CreatePartyViewModelDelegateImpl,
    private val selectAddressViewModelDelegateImpl: SelectAddressViewModelDelegateImpl
) : ViewModel(), CreatePartyViewModelDelegate by createPartyViewModelDelegateImpl,
    SelectAddressViewModelDelegate by selectAddressViewModelDelegateImpl {

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?>
        get() = _name

    init {
        val uiFriendAddress = createPartyViewModelDelegateImpl.getFriendAddressById(userId)
        _name.value = uiFriendAddress?.name
        setAddressName(uiFriendAddress?.addressName)
        setLatLng(uiFriendAddress?.latLng)
        setAddressId(uiFriendAddress?.addressId)
    }
}