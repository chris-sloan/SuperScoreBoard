package com.chrissloan.superscoreboard.fixtures.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

class FixturesViewModel(
    private val fixtureListRepository: FixtureListRepository,
    private val reduce: FixturesReducer,
) : ViewModel() {

    private var fixturesJob: Job? = null
    private val uiStateEmitter = MutableStateFlow(value = FixturesUiState())
    val uiState: Flow<FixturesUiState> = uiStateEmitter

    fun onLifeCycleResumed() {
        fixturesJob = fixturesEventFlow()
            .reduceState()
            .thenEmit()
            .launchIn(viewModelScope)
    }

    private fun fixturesEventFlow(): Flow<FixturesEvent> =
        merge(
            flowOf(FixturesEvent.Init),
            fixtureListRepository.getFixtures()
                .map {
                    FixturesEvent.FixturesLoaded(it)
                     },
        )

    private fun Flow<FixturesEvent>.reduceState() =
        map {
            reduce(uiStateEmitter.value, it)
        }

    private fun Flow<FixturesUiState>.thenEmit() =
        onEach {
            uiStateEmitter.value = it
        }

    fun onLifecyclePaused() {
        fixturesJob?.cancel()
    }
}