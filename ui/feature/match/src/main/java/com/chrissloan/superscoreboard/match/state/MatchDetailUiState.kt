package com.chrissloan.superscoreboard.match.state

data class MatchDetailUiState(
    val match: MatchState = MatchState(),
) {

    data class MatchState(
        val homeTeam: TeamState = TeamState(),
        val awayTeam: TeamState = TeamState(),
        val score: ScoreState = ScoreState(),
        val scorers: List<ScorerState> = emptyList(),
        val details: MatchDetail = MatchDetail(),
    ) {
        data class TeamState(
            val id: Int = -1,
            val name: String = "", // abbreviated name
        )

        data class ScoreState(
            val homeScore: Int = -1,
            val awayScore: Int = -1,
            val homeHtScore: Int = -1,
            val awayHtScore: Int = -1,
        )

        data class ScorerState(
            val playerName: String = "",
            val time: String = "",
            val homePlayer: Boolean = true,
        )

        data class MatchDetail(
            val kickOffTime: String = "00:00",
            val date: String = "",
            val location: String = "",
            val attendance: Int = 0,
            val referee: String = "",
        )
    }
}
