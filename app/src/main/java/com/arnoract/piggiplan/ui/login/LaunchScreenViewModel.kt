package com.arnoract.piggiplan.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchScreenViewModel : ViewModel() {

    private val _navigateToHomePageEvent = LiveEvent<Unit>()
    val navigateToHomePageEvent: LiveData<Unit>
        get() = _navigateToHomePageEvent

    init {
        viewModelScope.launch {
            delay(3000)
            _navigateToHomePageEvent.value = Unit
        }
    }
}