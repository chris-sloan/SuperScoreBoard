package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class Goal(
    val assistId: Int = 0,
    val clock: Clock = Clock(),
    val description: String = "",
    val personId: Int = 0,
    val phase: String = "",
    val type: String = ""
)