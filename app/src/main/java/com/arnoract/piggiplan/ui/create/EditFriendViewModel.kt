package com.arnoract.piggiplan.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.successOrThrow
import com.arnoract.piggiplan.domain.create.ValidateFriendAddressUseCase
import com.arnoract.piggiplan.domain.exception.InvalidFriendAddressException
import com.arnoract.piggiplan.ui.create.model.mapper.EditFriendToUiFriendAddressMapper
import com.google.android.gms.maps.model.LatLng
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditFriendViewModel(
    private val userId: Long,
    private val createPartyViewModelDelegateImpl: CreatePartyViewModelDelegateImpl,
    private val selectAddressViewModelDelegateImpl: SelectAddressViewModelDelegateImpl,
    private val validateFriendAddressUseCase: ValidateFriendAddressUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel(), CreatePartyViewModelDelegate by createPartyViewModelDelegateImpl,
    SelectAddressViewModelDelegate by selectAddressViewModelDelegateImpl {

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?>
        get() = _name

    private val _saveFriendSuccessEvent = LiveEvent<Unit>()
    val saveFriendSuccessEvent: LiveData<Unit>
        get() = _saveFriendSuccessEvent

    private val _isBlankFriendNameEvent = LiveEvent<Boolean>()
    val isBlankFriendNameEvent: LiveData<Boolean>
        get() = _isBlankFriendNameEvent

    private val _isBlankAddressNameEvent = LiveEvent<Boolean>()
    val isBlankAddressNameEvent: LiveData<Boolean>
        get() = _isBlankAddressNameEvent

    private val _saveFailExceptionEvent = LiveEvent<String>()
    val saveFailExceptionEvent: LiveData<String>
        get() = _saveFailExceptionEvent

    private val _isShowDelete = MutableLiveData<Boolean>()
    val isShowDelete: LiveData<Boolean>
        get() = _isShowDelete

    private val _showConfirmDeleteEvent = LiveEvent<Unit>()
    val showConfirmDeleteEvent: LiveData<Unit>
        get() = _showConfirmDeleteEvent

    private val _deleteSuccessEvent = LiveEvent<Unit>()
    val deleteSuccessEvent: LiveData<Unit>
        get() = _deleteSuccessEvent

    init {
        if (userId != 0L) {
            val uiFriendAddress = getFriendById(userId)
            _name.value = uiFriendAddress?.name
            setAddressName(uiFriendAddress?.addressName)
            setLatLng(uiFriendAddress?.latLng)
            setAddressId(uiFriendAddress?.addressId)
            _isShowDelete.value = true
        } else {
            _name.value = null
            setAddressId(null)
            setAddressName(null)
            setLatLng(null)
            _isShowDelete.value = false
        }
    }

    fun save(name: String) {
        viewModelScope.launch {
            try {
                withContext(coroutinesDispatcherProvider.io) {
                    validateFriendAddressUseCase.invoke(
                        ValidateFriendAddressUseCase.Params(
                            name = name,
                            addressName = getAddressName() ?: ""
                        )
                    ).successOrThrow()
                }
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
            } catch (e: InvalidFriendAddressException) {
                _isBlankFriendNameEvent.value = e.isBlankFriendName
                _isBlankAddressNameEvent.value = e.isBlankAddressName
            } catch (e: Exception) {
                _saveFailExceptionEvent.value = e.message ?: "Unknown Error"
            }
        }
    }

    fun delete() {
        deleteById(userId)
        _deleteSuccessEvent.value = Unit
    }

    fun showConfirmDelete() {
        _showConfirmDeleteEvent.value = Unit
    }
}