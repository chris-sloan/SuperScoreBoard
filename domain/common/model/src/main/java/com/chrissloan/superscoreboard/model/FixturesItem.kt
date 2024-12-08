package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class FixturesItem(
    val attendance: Int = 0,
    val clock: Clock = Clock(),
    val goals: List<Goal> = listOf(),
    val ground: Ground = Ground(),
    val id: Int = 0,
    val kickoff: Kickoff = Kickoff(),
    val status: String = "",
    val teams: List<Team> = listOf(),
)