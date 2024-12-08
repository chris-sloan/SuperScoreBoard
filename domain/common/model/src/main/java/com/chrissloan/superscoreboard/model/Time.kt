package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class Time(
    val label: String = "",
    val millis: Long = 0
)