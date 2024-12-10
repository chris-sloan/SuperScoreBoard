package com.chrissloan.superscoreboard.fixtures.viewmodel

import com.chrissloan.superscoreboard.model.Fixtures

sealed interface FixturesEvent {
    object Init : FixturesEvent
    data class FixturesLoaded(
        val fixtures: Fixtures,
    ) : FixturesEvent
}
