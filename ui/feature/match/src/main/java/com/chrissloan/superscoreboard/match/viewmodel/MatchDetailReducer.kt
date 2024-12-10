package com.chrissloan.superscoreboard.match.viewmodel

import com.chrissloan.superscoreboard.match.state.MatchDetailUiState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState
import com.chrissloan.superscoreboard.match.state.MatchDetailUiState.MatchState.MatchDetail
import com.chrissloan.superscoreboard.model.Match

class MatchDetailReducer {
    operator fun invoke(
        currentState: MatchDetailUiState,
        event: MatchDetailEvent,
    ): MatchDetailUiState =
        when (event) {
            is MatchDetailEvent.Init -> currentState
            is MatchDetailEvent.MatchDetailLoaded ->
                currentState.copy(match = event.match.toMatchState())
        }

    private fun Match.toMatchState(): MatchState {
        val homeTeam = MatchState.TeamState(teams.first().team.id, teams.first().team.club.abbr)
        val awayTeam = MatchState.TeamState(teams.last().team.id, teams.last().team.club.abbr)
        val score = MatchState.ScoreState(
            teams.first().score,
            teams.last().score,
            halfTimeScore.homeScore,
            halfTimeScore.awayScore
        )
        val scorersList = events
            .filter { it.type == "G" }
            .mapNotNull { event ->
                teamLists.firstOrNull {
                    it.teamId == event.teamId
                }?.lineup?.firstOrNull { lineup ->
                    lineup.id == event.personId
                }?.let {
                    val playerName = it.name.last
                    val eventTime = event.clock.label.substring(0, 3)
                    val isHome = event.teamId == homeTeam.id
                    MatchState.ScorerState(playerName, eventTime, isHome)
                }
            }

        val details = MatchDetail(
            kickOffTime = kickoff.label.split(",").lastOrNull()?.split(" ")?.dropWhile { it.isEmpty() }
                ?.first().orEmpty(),
            date = kickoff.label.split(",").first(),
            location = "${ground.name}, ${ground.city}",
            attendance = attendance,
            referee = matchOfficials.first().name.display,
        )

        return MatchState(
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            score = score,
            scorers = scorersList,
            details = details
        )
    }
}
