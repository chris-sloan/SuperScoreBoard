package com.chrissloan.superscoreboard.model


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