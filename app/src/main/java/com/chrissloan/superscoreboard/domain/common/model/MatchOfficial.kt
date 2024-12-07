package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchOfficial(
    val birth: Birth = Birth(),
    val id: Int = 0,
    val matchOfficialId: Int = 0,
    val name: Name = Name(),
    val role: String = ""
)