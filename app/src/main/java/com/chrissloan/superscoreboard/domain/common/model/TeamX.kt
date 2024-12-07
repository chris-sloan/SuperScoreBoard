package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamX(
    val altIds: AltIds = AltIds(),
    val club: Club = Club(),
    val id: Int = 0,
    val name: String = "",
    val shortName: String = "",
    val teamType: String = ""
)