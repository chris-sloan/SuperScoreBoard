package com.chrissloan.superscoreboard.model


import kotlinx.serialization.Serializable

@Serializable
data class Ground(
    val city: String = "",
    val id: Int = 0,
    val name: String = "",
    val source: String = ""
)