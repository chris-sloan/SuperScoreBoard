package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ground(
    val city: String = "",
    val id: Int = 0,
    val name: String = "",
    val source: String = ""
)