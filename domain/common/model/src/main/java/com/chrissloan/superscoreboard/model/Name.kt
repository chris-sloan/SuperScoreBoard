package com.chrissloan.superscoreboard.model

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val display: String = "",
    val first: String = "",
    val last: String = "",
    val middle: String = ""
)
