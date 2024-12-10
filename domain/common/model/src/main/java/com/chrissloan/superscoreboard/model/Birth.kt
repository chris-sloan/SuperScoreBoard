package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class Birth(
    val date: Date = Date(),
    val country: Country = Country(),
    val place: String = ""
)
