package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Club(
    val abbr: String = "",
    val id: Int = 0,
    val name: String = "",
    val shortName: String = ""
)