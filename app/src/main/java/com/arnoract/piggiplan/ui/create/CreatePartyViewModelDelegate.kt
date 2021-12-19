package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnoract.piggiplan.ui.create.model.UiFriendAddress

interface CreatePartyViewModelDelegate {
    val friends: LiveData<List<UiFriendAddress>>
    fun setFriends(friends: List<UiFriendAddress>)
    fun addFriend(data: UiFriendAddress)
    fun editFriend(id: Long, data: UiFriendAddress)
    fun getFriendById(id: Long): UiFriendAddress?
}

class CreatePartyViewModelDelegateImpl : CreatePartyViewModelDelegate {

    private val _friends = MutableLiveData<List<UiFriendAddress>>()
    override val friends: LiveData<List<UiFriendAddress>>
        get() = _friends

    override fun setFriends(friends: List<UiFriendAddress>) {
        _friends.value = friends
    }

    init {
        _friends.value = listOf()
    }

    override fun addFriend(data: UiFriendAddress) {
        val id = _friends.value?.size?.plus(1)?.toLong() ?: 0L
        _friends.value = _friends.value?.plus(
            data.copy(id = id)
        )
    }

    override fun editFriend(id: Long, data: UiFriendAddress) {
        _friends.value = _friends.value?.map {
            if (it.id == id) {
                data.copy(id = id)
            } else {
                it
            }
        }
    }

    override fun getFriendById(id: Long): UiFriendAddress? {
        return _friends.value?.firstOrNull {
            it.id == id
        }
    }
}