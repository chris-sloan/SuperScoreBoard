package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lineup(
    val age: String = "",
    val altIds: AltIds = AltIds(),
    val birth: Birth = Birth(),
    val captain: Boolean = false,
    val id: Int = 0,
    val info: Info = Info(),
    val matchPosition: String = "",
    val matchShirtNumber: Int = 0,
    val name: Name = Name(),
    val nationalTeam: NationalTeam = NationalTeam(),
    val playerId: Int = 0
)