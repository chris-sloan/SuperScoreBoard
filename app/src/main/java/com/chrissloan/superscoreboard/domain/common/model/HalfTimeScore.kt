package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HalfTimeScore(
    val awayScore: Int = 0,
    val homeScore: Int = 0
)