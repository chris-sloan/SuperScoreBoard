package com.chrissloan.superscoreboard.fixtures.viewmodel

import com.chrissloan.superscoreboard.fixtures.FixtureListRepository
import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState
import com.chrissloan.superscoreboard.mvi.UniDirectionalViewModel
import com.chrissloan.superscoreboard.useraction.FixturesAction
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class FixturesViewModel(
    private val fixtureListRepository: FixtureListRepository,
    private val fixturesEventHandler: FixturesEventHandler,
    private val fixturesReducer: FixturesReducer,
) : UniDirectionalViewModel<FixturesAction, FixturesEvent, FixturesUiState>() {

    override fun initialState() = FixturesUiState()

    override fun eventFlow() =
        merge(
            flowOf(FixturesEvent.Init),
            fixtureListRepository.getFixtures()
                .map {
                    FixturesEvent.FixturesLoaded(it)
                },
        )

    override fun eventHandled(event: FixturesEvent): Boolean = fixturesEventHandler(event)

    override fun reduce(
        currentState: FixturesUiState,
        event: FixturesEvent,
    ) = fixturesReducer(currentState, event)

    override fun onAction(action: FixturesAction) = fixturesActionHandler(action)

    /**
     * There could be a number of different dependencies that we would call from here.
     * Each dependency would then handle the call and if needed
     * emit a new event back into the view model's event flow ensuring unidirectional flow.
     */
    private fun fixturesActionHandler(action: FixturesAction) {
        when (action) {
            is FixturesAction.OnFixtureClick -> {
                /**
                 * We could fire some analytics here, for example.
                 * or trigger a video playback flow in another activity.
                 */
            }
        }
    }
}
