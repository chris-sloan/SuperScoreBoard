package com.chrissloan.superscoreboard.fixtures.viewmodel

import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState
import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState.FixtureState.MatchStatus
import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState.FixtureState.TeamState
import com.chrissloan.superscoreboard.fixtures.viewmodel.TestFixtures.completedFixturesItem
import com.chrissloan.superscoreboard.fixtures.viewmodel.TestFixtures.inProgressFixturesItem
import com.chrissloan.superscoreboard.fixtures.viewmodel.TestFixtures.upcomingFixturesItem
import com.chrissloan.superscoreboard.model.Fixtures
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FixturesReducerTest {
    private lateinit var reducer: FixturesReducer
    private lateinit var initialState: FixturesUiState

    @Before
    fun setUp() {
        reducer = FixturesReducer()
        initialState = FixturesUiState()
    }

    @Test
    fun `when event is Init, then state is unchanged`() {
        val newState = reducer.invoke(currentState = initialState, event = FixturesEvent.Init)

        assertEquals(initialState, newState)
    }

    @Test
    fun `when event is FixturesLoaded, then updated state is emitted`() {
        val fixtures = Fixtures(listOf(inProgressFixturesItem))
        val newState = reducer.invoke(
            currentState = initialState,
            event = FixturesEvent.FixturesLoaded(fixtures)
        )

        assertEquals(1, newState.fixtures.size)
        assertEquals(76, newState.fixtures[0].id)
        assertEquals(TeamState(id = 1, name = "HomeTeamName"), newState.fixtures[0].homeTeam)
        assertEquals(TeamState(id = 2, name = "AwayTeamName"), newState.fixtures[0].awayTeam)
    }

    @Test
    fun `given in progress fixture, when event is FixturesLoaded, then updated state is emitted`() {
        val fixtures = Fixtures(listOf(inProgressFixturesItem))
        val newState = reducer.invoke(
            currentState = initialState,
            event = FixturesEvent.FixturesLoaded(fixtures)
        )

        assertEquals(1, newState.fixtures.size)
        assertEquals(MatchStatus.InProgress("2 - 4", "testClockLabel"), newState.fixtures[0].status)
    }

    @Test
    fun `given completed fixture, when event is FixturesLoaded, then updated state is emitted`() {
        val fixtures = Fixtures(listOf(completedFixturesItem))
        val newState = reducer.invoke(
            currentState = initialState,
            event = FixturesEvent.FixturesLoaded(fixtures)
        )

        assertEquals(1, newState.fixtures.size)
        assertEquals(MatchStatus.Completed("2 - 4"), newState.fixtures[0].status)
    }

    @Test
    fun `given upcoming fixture, when event is FixturesLoaded, then updated state is emitted`() {
        val fixtures = Fixtures(listOf(upcomingFixturesItem))
        val newState = reducer.invoke(
            currentState = initialState,
            event = FixturesEvent.FixturesLoaded(fixtures)
        )

        assertEquals(1, newState.fixtures.size)
        assertEquals(MatchStatus.Upcoming("11:17"), newState.fixtures[0].status)
    }
}