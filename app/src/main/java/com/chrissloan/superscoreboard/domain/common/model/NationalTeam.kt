package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NationalTeam(
    val country: String = "",
    val demonym: String = "",
    val isoCode: String = ""
)