package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val score: Int = 0,
    val team: TeamX = TeamX()
)