package com.chrissloan.superscoreboard.domain.common.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val display: String = "",
    val first: String = "",
    val last: String = "",
    val middle: String = ""
)