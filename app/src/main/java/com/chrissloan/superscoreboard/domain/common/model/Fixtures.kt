package com.chrissloan.superscoreboard.domain.common.model

import kotlinx.serialization.Serializable


@Serializable
data class Fixtures(
    val fixtures: List<FixturesItem> = listOf()
) {
    companion object {
        val Empty = Fixtures()
    }
}