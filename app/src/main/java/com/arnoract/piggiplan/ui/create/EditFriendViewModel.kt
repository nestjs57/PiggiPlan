package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arnoract.piggiplan.ui.create.model.mapper.EditFriendToUiFriendAddressMapper
import com.google.android.gms.maps.model.LatLng
import com.hadilq.liveevent.LiveEvent

class EditFriendViewModel(
    private val userId: Long,
    private val createPartyViewModelDelegateImpl: CreatePartyViewModelDelegateImpl,
    private val selectAddressViewModelDelegateImpl: SelectAddressViewModelDelegateImpl
) : ViewModel(), CreatePartyViewModelDelegate by createPartyViewModelDelegateImpl,
    SelectAddressViewModelDelegate by selectAddressViewModelDelegateImpl {

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?>
        get() = _name

    private val _isBlankNameEvent = LiveEvent<Unit>()
    val isBlankNameEvent: LiveData<Unit>
        get() = _isBlankNameEvent

    private val _saveFriendSuccessEvent = LiveEvent<Unit>()
    val saveFriendSuccessEvent: LiveData<Unit>
        get() = _saveFriendSuccessEvent

    init {
        if (userId != 0L) {
            val uiFriendAddress = getFriendById(userId)
            _name.value = uiFriendAddress?.name
            setAddressName(uiFriendAddress?.addressName)
            setLatLng(uiFriendAddress?.latLng)
            setAddressId(uiFriendAddress?.addressId)
        } else {
            _name.value = null
            setAddressId(null)
            setAddressName(null)
            setLatLng(null)
        }
    }

    fun save(name: String) {
        if (name.isBlank()) {
            _isBlankNameEvent.value = Unit
        } else {
            val params = EditFriendToUiFriendAddressMapper.Params(
                name = name,
                addressId = getAddressId() ?: "",
                addressName = getAddressName() ?: "",
                latLng = getLatLng() ?: LatLng(0.0, 0.0)
            )
            if (userId == 0L) {
                addFriend(EditFriendToUiFriendAddressMapper.map(params))
            } else {
                editFriend(userId, EditFriendToUiFriendAddressMapper.map(params))
            }
            _saveFriendSuccessEvent.value = Unit
        }
    }
}