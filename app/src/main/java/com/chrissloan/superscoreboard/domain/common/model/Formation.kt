package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Formation(
    val label: String = "",
    val players: List<List<Int>> = listOf()
)