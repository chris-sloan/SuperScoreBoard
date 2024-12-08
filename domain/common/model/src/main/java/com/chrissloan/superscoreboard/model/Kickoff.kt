package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class Kickoff(
    val completeness: Int = 0,
    val label: String = "",
    val millis: Long = 0
)