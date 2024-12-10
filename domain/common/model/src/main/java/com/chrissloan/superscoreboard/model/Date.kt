package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class Date(
    val label: String = "",
    val millis: Long = 0
)
