package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class Club(
    val abbr: String = "",
    val id: Int = 0,
    val name: String = "",
    val shortName: String = ""
)
