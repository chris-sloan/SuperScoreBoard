package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class HalfTimeScore(
    val awayScore: Int = 0,
    val homeScore: Int = 0
)