package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class MatchOfficial(
    val birth: Birth = Birth(),
    val id: Int = 0,
    val matchOfficialId: Int = 0,
    val name: Name = Name(),
    val role: String = ""
)
