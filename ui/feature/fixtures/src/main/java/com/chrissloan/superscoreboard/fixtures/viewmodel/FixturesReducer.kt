package com.chrissloan.superscoreboard.fixtures.viewmodel

import com.chrissloan.superscoreboard.fixtures.state.FixturesUiState
import com.chrissloan.superscoreboard.model.FixturesItem
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class FixturesReducer {
    operator fun invoke(currentState: FixturesUiState, event: FixturesEvent): FixturesUiState {
        return when (event) {
            FixturesEvent.Init -> currentState
            is FixturesEvent.FixturesLoaded -> currentState.copy(fixtures = event.fixtures.fixtures.map { item ->
                val homeName =
                    FixturesUiState.FixtureState.TeamState(
                        id = item.teams[0].team.id,
                        name = item.teams[0].team.shortName
                    )
                val awayName =
                    FixturesUiState.FixtureState.TeamState(
                        id = item.teams[1].team.id,
                        name = item.teams[1].team.shortName
                    )
                val status = when (item.status) {
                    "U" -> FixturesUiState.FixtureState.MatchStatus.Upcoming(
                        formatFutureMatch(item)
                    )

                    "C" -> FixturesUiState.FixtureState.MatchStatus.Completed(
                        formatScore(item),
                    )

                    else -> FixturesUiState.FixtureState.MatchStatus.InProgress(
                        formatScore(item),
                        item.clock.label
                    )
                }
                FixturesUiState.FixtureState(
                    id = item.id,
                    homeTeam = homeName,
                    awayTeam = awayName,
                    status = status
                )
            })
        }
    }

    private fun formatScore(item: FixturesItem): String =
        "${item.teams[0].score} - ${item.teams[1].score}"

    private fun formatFutureMatch(item: FixturesItem): String {
        val match = Instant.Companion.fromEpochMilliseconds(item.kickoff.millis)
        val localDateTime = match.toLocalDateTime(TimeZone.Companion.currentSystemDefault())
        return "${localDateTime.hour.toString().padStart(2, '0')}:${
            localDateTime.minute.toString().padStart(2, '0')
        }"
    }
}