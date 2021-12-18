package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress

interface CreatePartyViewModelDelegate {
    val friends: LiveData<List<UiFriendAddress>>
}

class CreatePartyViewModelDelegateImpl : CreatePartyViewModelDelegate {

    private val _friends = MutableLiveData<List<UiFriendAddress>>()
    override val friends: LiveData<List<UiFriendAddress>>
        get() = _friends

    fun addFriendAddress(data: UiFriendAddress) {
        val id = _friends.value?.size ?: 0
        _friends.value?.plus(
            UiFriendAddress(
                id = id.toLong(),
                name = data.name,
                addressId = data.addressId,
                addressName = data.addressName,
                latLng = data.latLng
            )
        )
    }

    fun editFriendAddress(id: Long, data: UiFriendAddress) {
        _friends.value?.map {
            if (it.id == id) {
                it.copy(
                    id = data.id,
                    name = data.name,
                    addressName = data.addressName
                )
            } else {
                it
            }
        }
    }

    fun getFriendAddressById(id: Long): UiFriendAddress? {
        return _friends.value?.firstOrNull {
            it.id == id
        }
    }
}