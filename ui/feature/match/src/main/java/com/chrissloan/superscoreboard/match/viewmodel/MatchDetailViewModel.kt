package com.chrissloan.superscoreboard.match.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrissloan.superscoreboard.match.MatchDetailRepository
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState
import com.chrissloan.superscoreboard.model.Match
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

class MatchDetailViewModel(
    private val id: Int,
    private val matchDetailRepository: MatchDetailRepository,
    private val reduce: MatchDetailReducer,
) : ViewModel() {
    private val uiStateEmitter = MutableStateFlow(value = MatchDetailUiState())
    val uiState: Flow<MatchDetailUiState> = uiStateEmitter

    private var matchDetailJob: Job? = null

    fun onLifeCycleResumed() {
        matchDetailJob = fixturesEventFlow()
            .reduceState()
            .thenEmit()
            .launchIn(viewModelScope)
    }

    private fun fixturesEventFlow(): Flow<MatchDetailEvent> =
        merge(
            flowOf(MatchDetailEvent.Init),
            matchDetailRepository.getMatchDetail(id)
                .map {
                    MatchDetailEvent.MatchDetailLoaded(it)
                },
        )

    private fun Flow<MatchDetailEvent>.reduceState() =
        map {
            reduce(uiStateEmitter.value, it)
        }

    private fun Flow<MatchDetailUiState>.thenEmit() =
        onEach {
            uiStateEmitter.value = it
        }

    fun onLifecyclePaused() {
        matchDetailJob?.cancel()
    }
}
