package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class NationalTeam(
    val country: String = "",
    val demonym: String = "",
    val isoCode: String = ""
)
