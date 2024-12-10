package com.chrissloan.superscoreboard.match.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.chrissloan.superscoreboard.match.MatchDetailRepository
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState
import com.chrissloan.superscoreboard.mvi.UniDirectionalViewModel
import com.chrissloan.superscoreboard.useraction.MatchDetailAction
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class MatchDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    id: Int,
    private val matchDetailRepository: MatchDetailRepository,
    private val matchDetailReducer: MatchDetailReducer,
) : UniDirectionalViewModel<MatchDetailAction, MatchDetailEvent, MatchDetailUiState>() {

    val matchId by lazy { savedStateHandle.get<Int>("matchId") }
    init {
        savedStateHandle["matchId"] = id
    }

    override fun initialState(): MatchDetailUiState = MatchDetailUiState()

    override suspend fun eventFlow() = merge(
        flowOf(MatchDetailEvent.Init),
        matchDetailRepository.getMatchDetail(matchId!!)
            .map {
                MatchDetailEvent.MatchDetailLoaded(it)
            },
    )

    override fun eventHandled(event: MatchDetailEvent) = false // all events update UI State

    override fun reduce(
        currentState: MatchDetailUiState,
        event: MatchDetailEvent,
    ): MatchDetailUiState = matchDetailReducer(currentState, event)

    override fun onAction(action: MatchDetailAction) {
        // Handle user actions
    }
}
