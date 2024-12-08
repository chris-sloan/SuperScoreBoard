package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class TeamLists(
    val formation: Formation = Formation(),
    val lineup: List<Lineup> = listOf(),
    val substitutes: List<Substitute> = listOf(),
    val teamId: Int = 0
)