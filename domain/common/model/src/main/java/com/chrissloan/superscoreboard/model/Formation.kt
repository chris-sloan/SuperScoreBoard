package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class Formation(
    val label: String = "",
    val players: List<List<Int>> = listOf()
)