package com.chrissloan.superscoreboard.fixtures.state

data class FixturesUiState(
    val fixtures: List<FixtureState> = emptyList(),
) {
    data class FixtureState(
        val id: Int,
        val homeTeam: TeamState,
        val awayTeam: TeamState,
        val status: MatchStatus,
    ) {
        data class TeamState(
            val id: Int,
            val name: String,
        )

        sealed interface MatchStatus {
            data class Upcoming(
                val time: String,
            ) : MatchStatus

            data class InProgress(
                val score: String,
                val time: String,
            ) : MatchStatus

            data class Completed(
                val score: String,
            ) : MatchStatus
        }
    }
}