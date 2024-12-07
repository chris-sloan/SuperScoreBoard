package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val attendance: Int = 0,
    val clock: Clock = Clock(),
    val events: List<Event> = listOf(),
    val ground: Ground = Ground(),
    val halfTimeScore: HalfTimeScore = HalfTimeScore(),
    val id: Int = 0,
    val kickoff: Kickoff = Kickoff(),
    val matchOfficials: List<MatchOfficial> = listOf(),
    val status: String = "",
    val teamLists: List<TeamLists> = listOf(),
    val teams: List<Team> = listOf()
)