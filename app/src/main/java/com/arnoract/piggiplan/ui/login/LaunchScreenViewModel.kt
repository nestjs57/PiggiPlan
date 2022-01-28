package com.arnoract.piggiplan.ui.login

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.successOrThrow
import com.arnoract.piggiplan.domain.launchscreen.InitialFetchUseCase
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class LaunchScreenViewModel(
    private val initialFetchUseCase: InitialFetchUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _navigateToHomePageEvent = LiveEvent<Unit>()
    val navigateToHomePageEvent: LiveData<Unit>
        get() = _navigateToHomePageEvent

    private val _initialFetchDataFailEvent = LiveEvent<String>()
    val initialFetchDataFailEvent: LiveData<String>
        get() = _initialFetchDataFailEvent

    init {
        viewModelScope.launch {
            try {
                withContext(coroutinesDispatcherProvider.io) {
                    initialFetchUseCase.invoke(Unit).successOrThrow()
                }
                _navigateToHomePageEvent.value = Unit
            } catch (e: Exception) {
                _initialFetchDataFailEvent.value = e.message ?: "Unknown Error"
            }
        }
    }
}