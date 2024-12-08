package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class Clock(
    val label: String = "",
    val secs: Int = 0
)