package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val loan: Boolean = false,
    val position: String = "",
    val positionInfo: String = "",
    val shirtNum: Int = 0
)