package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val assistId: Int = 0,
    val clock: Clock = Clock(),
    val description: String = "",
    val id: Int = 0,
    val personId: Int = 0,
    val phase: String = "",
    val score: Score = Score(),
    val teamId: Int = 0,
    val time: Time = Time(),
    val type: String = ""
)
