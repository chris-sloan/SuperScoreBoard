package com.chrissloan.superscoreboard.match.viewmodel

import com.chrissloan.superscoreboard.match.state.MatchDetailUiState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.MatchDetail
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.ScoreState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.ScorerState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.TeamState
import com.chrissloan.superscoreboard.match.viewmodel.TestFixtures.sampleMatch
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MatchDetailReducerTest {

    private lateinit var reducer: MatchDetailReducer
    private lateinit var initialState: MatchDetailUiState

    @Before
    fun setUp() {
        reducer = MatchDetailReducer()
        initialState = MatchDetailUiState()
    }

    @Test
    fun `Init event returns current state`() {
        val event = MatchDetailEvent.Init

        val newState = reducer.invoke(initialState, event)

        assertEquals(initialState, newState)
    }

    @Test
    fun `MatchDetailLoaded event updates state with match data`() {
        val match = sampleMatch
        val event = MatchDetailEvent.MatchDetailLoaded(match)

        val newState = reducer.invoke(initialState, event)

        assertEquals(TeamState(1, "LIV"), newState.match.homeTeam)
        assertEquals(TeamState(2, "MUN"), newState.match.awayTeam)
        assertEquals(
            ScoreState(homeScore = 2, awayScore = 1, homeHtScore = 1, awayHtScore = 0), newState.match.score
        )
        assertEquals(
            listOf(
                ScorerState("Salah", "10'", true),
                ScorerState("Mane", "45'", true),
                ScorerState("Ronaldo", "70'", false)
            ),
            newState.match.scorers
        )
        assertEquals(
            MatchDetail(
                kickOffTime = "15:00",
                date = "Saturday 10 September 2023",
                location = "Anfield, Liverpool",
                attendance = 53394,
                referee = "Michael Oliver"
            ),
            newState.match.details
        )
    }
}