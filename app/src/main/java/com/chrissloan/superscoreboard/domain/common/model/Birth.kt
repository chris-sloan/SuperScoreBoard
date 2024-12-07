package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Birth(
    val date: Date = Date(),
    val country: Country = Country(),
    val place: String = ""
)