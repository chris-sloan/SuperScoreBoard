package com.chrissloan.superscoreboard.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrissloan.superscoreboard.useraction.UserAction
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

abstract class UniDirectionalViewModel<Action : UserAction, Event : Any, UiState : Any> :
    ViewModel() {

    private var eventFlowJob: Job? = null
    private val uiStateEmitter = MutableStateFlow<UiState>(value = initialState())
    val uiState: Flow<UiState> = uiStateEmitter

    abstract fun initialState(): UiState
    abstract fun eventFlow(): Flow<Event>
    abstract fun eventHandled(event: Event): Boolean
    abstract fun reduce(currentState: UiState, event: Event): UiState
    abstract fun onAction(action: Action)

    fun onLifeCycleResumed() {
        eventFlowJob = eventFlow()
            .handleSideEffect()
            .reduceState()
            .thenEmit()
            .launchIn(viewModelScope)
    }

    private fun Flow<Event>.handleSideEffect() =
        transform {
            if (!eventHandled(it)) {
                emit(it)
            }
        }

    private fun Flow<Event>.reduceState() =
        map {
            reduce(uiStateEmitter.value, it)
        }

    private fun Flow<UiState>.thenEmit() =
        onEach {
            uiStateEmitter.value = it
        }

    fun onLifecyclePaused() {
        eventFlowJob?.cancel()
    }
}
