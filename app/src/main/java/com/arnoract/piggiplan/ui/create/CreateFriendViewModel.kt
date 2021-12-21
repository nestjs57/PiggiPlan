package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.ViewModel

class CreateFriendViewModel(
    private val createPartyViewModelDelegateImpl: CreatePartyViewModelDelegateImpl
) : ViewModel(), CreatePartyViewModelDelegate by createPartyViewModelDelegateImpl {

    init {
        setFriends(listOf())
    }
}