package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kickoff(
    val completeness: Int = 0,
    val label: String = "",
    val millis: Long = 0
)